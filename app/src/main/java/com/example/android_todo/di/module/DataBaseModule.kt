package com.example.android_todo.di.module

import android.content.Context
import androidx.room.Room
import com.example.android_todo.data.source.local.AppDataBase
import com.example.android_todo.data.source.local.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(applicationContext: Context): AppDataBase =
        Room.databaseBuilder(applicationContext, AppDataBase::class.java, "todo.db").build()

    @Singleton
    @Provides
    fun provideTodoDao(appDatabase: AppDataBase): TodoDao = appDatabase.todoDao()
}
