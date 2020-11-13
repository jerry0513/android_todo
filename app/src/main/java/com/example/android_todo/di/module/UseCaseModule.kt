package com.example.android_todo.di.module

import com.example.android_todo.domain.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindSearchTodoListUseCase(searchTodoListUseCase: SearchTodoListUseCase): BaseUseCase

    @Singleton
    @Binds
    abstract fun bindEditTodoUseCase(editTodoUseCase: EditTodoUseCase): BaseUseCase

    @Singleton
    @Binds
    abstract fun bindDeleteTodoUseCase(deleteTodoUseCase: DeleteTodoUseCase): BaseUseCase
}
