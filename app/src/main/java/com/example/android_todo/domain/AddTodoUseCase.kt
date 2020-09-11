package com.example.android_todo.domain

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.TodoRepository
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(private val todoRepository: TodoRepository) : BaseUseCase() {

    suspend fun addTodo(params: Params) {
        todoRepository.insertTodo(params.toTodoEntity())
    }

    data class Params(
        val title: String,
        val description: String?,
        val eventTime: Long? = null
    ) {
        fun toTodoEntity(): TodoEntity {
            return TodoEntity(title = title, description = description, eventTime = eventTime)
        }
    }
}
