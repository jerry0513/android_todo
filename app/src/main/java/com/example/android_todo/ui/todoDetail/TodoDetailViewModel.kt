package com.example.android_todo.ui.todoDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.domain.GetTodoDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoDetailViewModel @Inject constructor(private val getTodoDetailUseCase: GetTodoDetailUseCase) :
    ViewModel() {

    val todo = MutableLiveData<TodoEntity>()

    fun getTodoDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todo.postValue(getTodoDetailUseCase(id))
        }
    }
}