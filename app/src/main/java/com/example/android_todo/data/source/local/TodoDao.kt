package com.example.android_todo.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_todo.data.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME}")
    fun getAll(): List<TodoEntity>

    @Insert
    fun insert(todoEntity: TodoEntity)

    @Delete
    fun delete(todoEntity: TodoEntity)
}