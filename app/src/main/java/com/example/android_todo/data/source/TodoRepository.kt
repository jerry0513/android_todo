package com.example.android_todo.data.source

import com.example.android_todo.data.Todo
import java.util.*
import javax.inject.Inject

class TodoRepository @Inject constructor() : Repository {
    fun fetchTodoList(): List<Todo> {
        return listOf(
            Todo("Hello", "Hello world", Date().time),
            Todo("Hi", "Hi hi hi", Date().time),
            Todo( "I'm tired", "gonna sleep", Date().time)
        )
    }
}

interface Repository