package com.example.android_todo.ui.todoEdit

import androidx.lifecycle.*
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.DeleteTodoUseCase
import com.example.android_todo.domain.EditTodoUseCase
import com.soywiz.klock.DateTime
import com.soywiz.klock.minutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoEditViewModel @Inject constructor(
    private val editTodoUseCase: EditTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    val id = MutableLiveData<Int>(null)
    val enabledDelete: LiveData<Boolean> = Transformations.map(id) { it != null }

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    /** unix unit */
    val date = MutableLiveData(DateTime.now().startOfDay.unixMillisLong)

    /** minute unit*/
    val time = MutableLiveData(DateTime.now().run {
        hours * 60 + minutes
    })

    val status = MutableLiveData<Result<String>>()

    fun editTodo() {
        viewModelScope.launch {
            status.postValue(Result.Loading)
            delay(2000)

            try {
                validateInput()

                val dateTime = DateTime.fromUnix(date.value!!) + time.value!!.minutes
                val params = TodoEntity(
                    id = id.value,
                    title = title.value!!,
                    description = description.value,
                    eventTime = dateTime.unixMillisLong
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
        if (title.value.isNullOrEmpty()) {
            throw NullPointerException("please fill in the title")
        }
    }

    fun setTodo(todo: TodoEntity) {
        id.value = todo.id
        title.value = todo.title
        description.value = todo.description

        val dateTime = DateTime.fromUnix(todo.eventTime)
        date.value = dateTime.startOfDay.unixMillisLong
        time.value = dateTime.hours * 60 + dateTime.minutes
    }
}
