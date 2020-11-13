package com.example.android_todo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.data.source.local.AppDataBase
import com.example.android_todo.data.source.local.TodoDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TodoDaoTest {

    private lateinit var db: AppDataBase
    private lateinit var todoDao: TodoDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        todoDao = db.todoDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndSearchTodo() = runBlocking {
        val insertedTodo = insertTodo()
        val searchedTodo = todoDao.search("hello")
            .take(1)
            .first()
        assertEquals(insertedTodo, searchedTodo.first())
    }

    @Test
    fun deleteTodo() = runBlocking {
        insertTodo()
        todoDao.delete(1)
        val searched = todoDao.search("hello")
            .take(1)
            .first()
        assertTrue(searched.isEmpty())
    }

    private suspend fun insertTodo(): TodoEntity {
        val now = System.currentTimeMillis()
        val eventTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(now),
            ZoneId.systemDefault()
        )
        val entity = TodoEntity(id = 1, title = "hello", eventTime = eventTime)
        todoDao.insert(entity)
        return entity
    }
}