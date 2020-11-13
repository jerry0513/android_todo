package com.example.android_todo.data.source

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.local.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) : BaseRepository() {

    suspend fun fetchTodoList(): List<TodoEntity> = todoDao.getAll()

    fun searchTodoList(title: String): Flow<List<TodoEntity>> =
        todoDao.searchDistinctUntilChanged(title)

    suspend fun insertTodo(todoEntity: TodoEntity) = todoDao.insert(todoEntity)

    suspend fun deleteTodo(id: Int) = todoDao.delete(id)
}
