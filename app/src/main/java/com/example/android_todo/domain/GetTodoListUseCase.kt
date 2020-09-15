package com.example.android_todo.domain

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.TodoRepository
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(private val todoRepo: TodoRepository) : BaseUseCase() {

    suspend operator fun invoke(): List<TodoEntity> = todoRepo.fetchTodoList()
}
