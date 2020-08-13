package com.example.android_todo.data.source

import com.example.android_todo.data.Todo
import java.util.*
import javax.inject.Inject

class TodoRepository @Inject constructor() : Repository {

    private val data = listOf(
        Todo(0, "Hello", "Hello world", Date().time),
        Todo(1, "Hi", "Hi hi hi", Date().time),
        Todo(2, "I'm tired", "gonna sleep", Date().time)
    )

    fun fetchTodoList(): List<Todo> {
        return data
    }

    fun fetchTodo(id: Int): Todo {
        return data.first { todo ->
            todo.id == id
        }
    }
}

interface Repository