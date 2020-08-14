package com.example.android_todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_todo.data.TodoEntity.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title: String,
    var description: String? = null,
    var eventTime: Long? = null
) : Parcelable {
    companion object {
        const val TABLE_NAME = "todo_entity"
    }
}