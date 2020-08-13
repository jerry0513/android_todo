package com.example.android_todo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_todo.data.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}