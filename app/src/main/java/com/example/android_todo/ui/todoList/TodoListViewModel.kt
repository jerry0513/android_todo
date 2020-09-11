package com.example.android_todo.ui.todoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.GetTodoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoListViewModel @Inject constructor(private val getTodoListUseCase: GetTodoListUseCase) :
    ViewModel() {

    val todoList = MutableLiveData<Result<List<TodoEntity>>>()

    fun getTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            todoList.postValue(Result.Loading)
            delay(2000)
            todoList.postValue(Result.Success(getTodoListUseCase()))
        }
    }
}