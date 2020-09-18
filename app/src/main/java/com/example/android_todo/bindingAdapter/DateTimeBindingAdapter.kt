package com.example.android_todo.bindingAdapter

import android.widget.TextView
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun TextView.setDateTime(dateTime: LocalDateTime?) {
    dateTime?.let {
        text = it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }
}

fun TextView.setDate(date: LocalDateTime?) {
    date?.let {
        text = it.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }
}

fun TextView.setTime(time: LocalDateTime?) {
    time?.let {
        text = it.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
