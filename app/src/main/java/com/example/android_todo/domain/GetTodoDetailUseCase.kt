package com.example.android_todo.domain

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.TodoRepository
import javax.inject.Inject

class GetTodoDetailUseCase @Inject constructor(private val todoRepo: TodoRepository) : BaseUseCase() {
    operator fun invoke(id: Int): TodoEntity {
        return todoRepo.fetchTodo(id)
    }
}