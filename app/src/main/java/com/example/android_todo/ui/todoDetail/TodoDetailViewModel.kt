package com.example.android_todo.ui.todoDetail

import androidx.lifecycle.ViewModel
import com.example.android_todo.domain.GetTodoDetailUseCase
import javax.inject.Inject

class TodoDetailViewModel @Inject constructor(private val getTodoDetailUseCase: GetTodoDetailUseCase) :
    ViewModel() {

    fun getTodoDetail(id: Int) = getTodoDetailUseCase(id)
}