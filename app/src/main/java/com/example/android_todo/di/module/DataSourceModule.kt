package com.example.android_todo.di.module

import com.example.android_todo.data.source.Repository
import com.example.android_todo.data.source.TodoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: TodoRepository): Repository
}
