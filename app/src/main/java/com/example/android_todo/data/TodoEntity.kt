package com.example.android_todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_todo.data.TodoEntity.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
@Entity(tableName = TABLE_NAME)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String? = null,
    val eventTime: LocalDateTime
) : Parcelable {
    companion object {
        const val TABLE_NAME = "todo_entity"
    }
}
