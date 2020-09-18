package com.example.android_todo.ui.todoEdit

import androidx.lifecycle.*
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.DeleteTodoUseCase
import com.example.android_todo.domain.EditTodoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import javax.inject.Inject

class TodoEditViewModel @Inject constructor(
    private val editTodoUseCase: EditTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    val id = MutableLiveData<Int>(null)
    val enabledDelete: LiveData<Boolean> = Transformations.map(id) { it != null }

    val title = MutableLiveData<String>(null)
    val description = MutableLiveData<String>(null)

    val date = MutableLiveData(LocalDate.now())
    val time = MutableLiveData(LocalTime.now())
    val dateTime = MediatorLiveData<LocalDateTime>().apply {
        addSource(date) { date ->
            value = date.atTime(time.value!!.hour, time.value!!.minute)
        }
        addSource(time) { time ->
            value = date.value!!.atTime(time.hour, time.minute)
        }
    }

    val status = MutableLiveData<Result<String>>()

    fun editTodo() {
        viewModelScope.launch {
            status.postValue(Result.Loading)
            delay(2000)

            try {
                validateInput()

                val params = TodoEntity(
                    id = id.value,
                    title = title.value!!,
                    description = description.value,
                    eventTime = dateTime.value!!
                )
                editTodoUseCase(params)
                status.postValue(Result.Success())
            } catch (e: Exception) {
                status.postValue(Result.Failed("$e"))
            }
        }
    }

    fun deleteTodo() {
        viewModelScope.launch {
            status.postValue(Result.Loading)
            delay(2000)

            try {
                deleteTodoUseCase(id.value!!)
                status.postValue(Result.Success())
            } catch (e: Exception) {
                status.postValue(Result.Failed("$e"))
            }
        }
    }

    private fun validateInput() {
        if (title.value.isNullOrEmpty())
            throw NullPointerException("please fill in the title")
    }

    fun setTodo(todo: TodoEntity) {
        id.value = todo.id
        title.value = todo.title
        description.value = todo.description
        date.value = todo.eventTime.toLocalDate()
        time.value = todo.eventTime.toLocalTime()
    }
}
