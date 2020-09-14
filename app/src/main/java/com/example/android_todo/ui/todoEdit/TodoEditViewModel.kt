package com.example.android_todo.ui.todoEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.EditTodoUseCase
import com.soywiz.klock.DateTime
import com.soywiz.klock.minutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoEditViewModel @Inject constructor(private val editTodoUseCase: EditTodoUseCase) :
    ViewModel() {

    private var id: Int? = null
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
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(Result.Loading)
            delay(2000)


            val dateTime = DateTime.fromUnix(date.value!!) + time.value!!.minutes
            val params = TodoEntity(
                id = id,
                title = title.value!!,
                description = description.value,
                eventTime = dateTime.unixMillisLong
            )
            editTodoUseCase(params)

            status.postValue(Result.Success(""))
        }
    }

    fun setTodo(todo: TodoEntity) {
        id = todo.id
        title.value = todo.title
        description.value = todo.description

        val dateTime = DateTime.fromUnix(todo.eventTime)
        date.value = dateTime.startOfDay.unixMillisLong
        time.value = dateTime.hours * 60 + dateTime.minutes
    }
}
