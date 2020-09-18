package com.example.android_todo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_todo.data.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}
