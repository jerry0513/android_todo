package com.example.android_todo.domain

import com.example.android_todo.data.source.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(private val todoRepository: TodoRepository) :
    BaseUseCase() {

    suspend operator fun invoke(id: Int) = todoRepository.deleteTodo(id)
}
