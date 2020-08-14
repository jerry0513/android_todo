package com.example.android_todo.domain

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.TodoRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(private val todoRepo: TodoRepository) : BaseUseCase() {
    suspend operator fun invoke(): Flow<List<TodoEntity>> {
        return todoRepo.fetchTodoList()
    }
}

