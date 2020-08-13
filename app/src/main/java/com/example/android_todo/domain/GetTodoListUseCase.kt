package com.example.android_todo.domain

import com.example.android_todo.data.Todo
import com.example.android_todo.data.source.TodoRepository
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(private val todoRepo: TodoRepository) : BaseUseCase() {
    operator fun invoke(): List<Todo> {
        return todoRepo.fetchTodoList()
    }
}

