package com.example.android_todo.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_todo.data.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class TodoDao {

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME} ORDER BY eventTime DESC")
    abstract suspend fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM ${TodoEntity.TABLE_NAME} WHERE title LIKE '%' || :title || '%' ORDER BY eventTime DESC")
    abstract fun search(title: String): Flow<List<TodoEntity>>
    fun searchDistinctUntilChanged(title: String) = search(title).distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(todoEntity: TodoEntity)

    @Query("DELETE FROM ${TodoEntity.TABLE_NAME} WHERE id = :id")
    abstract suspend fun delete(id: Int)
}
