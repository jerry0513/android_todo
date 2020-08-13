package com.example.android_todo.data.source

import com.example.android_todo.data.TodoEntity
import java.util.*
import javax.inject.Inject

class TodoRepository @Inject constructor() : BaseRepository() {

    private val data = listOf(
        TodoEntity(0, "Hello", "Hello world", Date().time),
        TodoEntity(1, "Hi", "Hi hi hi", Date().time),
        TodoEntity(2, "I'm tired", "gonna sleep", Date().time)
    )

    fun fetchTodoList(): List<TodoEntity> {
        return data
    }

    fun fetchTodo(id: Int): TodoEntity {
        return data.first { todo ->
            todo.id == id
        }
    }
}
