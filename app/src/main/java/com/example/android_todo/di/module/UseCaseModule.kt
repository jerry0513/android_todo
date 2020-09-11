package com.example.android_todo.di.module

import com.example.android_todo.domain.GetTodoDetailUseCase
import com.example.android_todo.domain.GetTodoListUseCase
import com.example.android_todo.domain.BaseUseCase
import com.example.android_todo.domain.EditTodoUseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetTodoListUseCase(getTodoListUseCase: GetTodoListUseCase): BaseUseCase

    @Singleton
    @Binds
    abstract fun bindGetTodoDetailUseCase(getTodoDetailUseCase: GetTodoDetailUseCase): BaseUseCase

    @Singleton
    @Binds
    abstract fun bindEditTodoUseCase(editTodoUseCase: EditTodoUseCase): BaseUseCase
}
