package com.example.android_todo.data.source.local

import androidx.room.*
import com.example.android_todo.data.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME}")
    suspend fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME} WHERE id = :id")
    fun get(id: Int): TodoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoEntity: TodoEntity)

    @Delete
    fun delete(todoEntity: TodoEntity)
}