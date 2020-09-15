package com.example.android_todo.domain

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.TodoRepository
import javax.inject.Inject

class EditTodoUseCase @Inject constructor(private val todoRepository: TodoRepository) :
    BaseUseCase() {

    suspend operator fun invoke(params: TodoEntity) = todoRepository.insertTodo(params)
}
