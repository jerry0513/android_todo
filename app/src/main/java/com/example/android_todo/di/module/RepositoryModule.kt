package com.example.android_todo.di.module

import com.example.android_todo.data.source.BaseRepository
import com.example.android_todo.data.source.TodoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideTodoRepository(todoRepository: TodoRepository): BaseRepository
}