package com.example.android_todo.ui.todoEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.AddTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class TodoEditViewModel @Inject constructor(private val addTodoUseCase: AddTodoUseCase) : ViewModel() {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val status = MutableLiveData<Result<String>>()

    fun addTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            val params = AddTodoUseCase.Params(
                title = title.value!!,
                description = description.value!!
            )
            addTodoUseCase.addTodo(params)
            status.postValue(Result.Success(""))
        }
    }
}