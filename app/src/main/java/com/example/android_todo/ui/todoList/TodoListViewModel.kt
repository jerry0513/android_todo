package com.example.android_todo.ui.todoList

import androidx.lifecycle.ViewModel
import com.example.android_todo.domain.GetTodoListUseCase
import javax.inject.Inject

class TodoListViewModel @Inject constructor(private val getTodoListUseCase: GetTodoListUseCase) :
    ViewModel() {

    fun getTodoList() = getTodoListUseCase()
}