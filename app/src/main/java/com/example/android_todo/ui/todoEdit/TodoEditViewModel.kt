package com.example.android_todo.ui.todoEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.Result
import com.example.android_todo.domain.AddTodoUseCase
import com.soywiz.klock.DateTime
import com.soywiz.klock.minutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoEditViewModel @Inject constructor(private val addTodoUseCase: AddTodoUseCase) : ViewModel() {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val date = MutableLiveData(DateTime.nowUnixLong())
    val time = MutableLiveData(DateTime.nowLocal().run {
        hours * 60 + minutes
    })

    val status = MutableLiveData<Result<String>>()

    fun addTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(Result.Loading)
            delay(2000)

            val dateTime = DateTime.fromUnix(date.value!!) + time.value!!.minutes
            addTodoUseCase.addTodo(
                AddTodoUseCase.Params(
                    title = title.value!!,
                    description = description.value!!,
                    eventTime = dateTime.unixMillisLong
                )
            )

            status.postValue(Result.Success(""))
        }
    }
}
