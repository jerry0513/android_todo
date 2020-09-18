package com.example.android_todo.data.source.local

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset

class DateTimeConverter {

    @TypeConverter
    fun fromUnixMillis(value: Long): LocalDateTime =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())

    @TypeConverter
    fun toUnixMillis(value: LocalDateTime): Long = value.toInstant(ZoneOffset.UTC).toEpochMilli()
}