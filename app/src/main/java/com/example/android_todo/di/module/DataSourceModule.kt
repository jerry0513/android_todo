package com.example.android_todo.di.module

import android.content.Context
import androidx.room.Room
import com.example.android_todo.data.source.BaseRepository
import com.example.android_todo.data.source.TodoRepository
import com.example.android_todo.data.source.local.AppDataBase
import com.example.android_todo.data.source.local.TodoDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module(includes = [DispatcherModule::class])
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
    fun provideTodoDao(appDatabase: AppDataBase): TodoDao = appDatabase.todoDao()

    @Singleton
    @Provides
    fun provideTodoRepository(@IoDispatcher ioDispatcher: CoroutineDispatcher, todoDao: TodoDao): BaseRepository = TodoRepository(ioDispatcher, todoDao)
}
