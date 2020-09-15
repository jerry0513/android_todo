package com.example.android_todo.ui.todoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.GetTodoListUseCase
import com.example.android_todo.domain.SearchTodoListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val searchTodoListUseCase: SearchTodoListUseCase
) : ViewModel() {

    val todoList = MutableLiveData<Result<List<TodoEntity>>>()

    fun getTodoList() {
        viewModelScope.launch {
            todoList.postValue(Result.Loading)
            delay(2000)

            try {
                todoList.postValue(Result.Success(getTodoListUseCase()))
            } catch (e: Exception) {
                todoList.postValue(Result.Failed("$e"))
            }
        }
    }

    fun searchTodoList(title: String) {
        viewModelScope.launch {
            searchTodoListUseCase(title).collect {
                todoList.postValue(Result.Success(it))
            }
        }
    }
}
