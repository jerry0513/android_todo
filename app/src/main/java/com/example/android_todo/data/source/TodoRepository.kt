package com.example.android_todo.data.source

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.local.TodoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) : BaseRepository() {

    suspend fun fetchTodoList(): Flow<List<TodoEntity>> {
        return todoDao.getAll()
    }

    suspend fun fetchTodo(id: Int): TodoEntity {
        return todoDao.get(id)
    }

    suspend fun insertTodo(todoEntity: TodoEntity) {
        todoDao.insert(todoEntity)
    }
}
