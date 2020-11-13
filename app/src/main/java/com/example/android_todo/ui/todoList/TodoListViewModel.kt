package com.example.android_todo.ui.todoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.Result
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.SearchTodoListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
    private val searchTodoListUseCase: SearchTodoListUseCase
) : ViewModel() {

    val todoList = MutableLiveData<Result<List<TodoEntity>>>()

    fun searchTodoList(title: String) {
        viewModelScope.launch {
            searchTodoListUseCase(title).collect {
                todoList.postValue(Result.Success(it))
            }
        }
    }
}
