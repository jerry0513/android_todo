package com.example.android_todo.di.module

import com.example.android_todo.domain.GetTodoListUseCase
import com.example.android_todo.domain.UseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetTodoUseCase(useCase: GetTodoListUseCase): UseCase
}