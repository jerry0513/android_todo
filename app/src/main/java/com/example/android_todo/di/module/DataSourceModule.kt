package com.example.android_todo.di.module

import android.content.Context
import androidx.room.Room
import com.example.android_todo.data.source.BaseRepository
import com.example.android_todo.data.source.TodoRepository
import com.example.android_todo.data.source.local.AppDataBase
import com.example.android_todo.data.source.local.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideAppDataBase(applicationContext: Context): AppDataBase {
        return Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "todo.db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesTodoDao(appDatabase: AppDataBase): TodoDao = appDatabase.todoDao()

    @Singleton
    @Provides
    fun provideTodoRepository(): BaseRepository = TodoRepository()
}
