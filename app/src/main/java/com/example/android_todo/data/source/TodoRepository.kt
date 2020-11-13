package com.example.android_todo.data.source

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.local.TodoDao
import com.example.android_todo.di.module.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val todoDao: TodoDao
) : BaseRepository() {

    fun searchTodoList(title: String): Flow<List<TodoEntity>> =
        todoDao.searchDistinctUntilChanged(title).flowOn(ioDispatcher)

    suspend fun insertTodo(todoEntity: TodoEntity) = withContext(ioDispatcher) {
        todoDao.insert(todoEntity)
    }

    suspend fun deleteTodo(id: Int) = withContext(ioDispatcher) {
        todoDao.delete(id)
    }
}
