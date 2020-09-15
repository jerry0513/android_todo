package com.example.android_todo.data.source

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.local.TodoDao
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) : BaseRepository() {

    suspend fun fetchTodoList(): List<TodoEntity> {
        return todoDao.getAll()
    }

    suspend fun insertTodo(todoEntity: TodoEntity) {
        todoDao.insert(todoEntity)
    }

    suspend fun deleteTod(id: Int) {
        todoDao.delete(id)
    }
}
