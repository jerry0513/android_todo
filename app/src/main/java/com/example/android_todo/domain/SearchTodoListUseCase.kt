package com.example.android_todo.domain

import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchTodoListUseCase @Inject constructor(private val todoRepo: TodoRepository) :
    BaseUseCase() {

    operator fun invoke(title: String): Flow<List<TodoEntity>> = todoRepo.searchTodoList(title)
}
