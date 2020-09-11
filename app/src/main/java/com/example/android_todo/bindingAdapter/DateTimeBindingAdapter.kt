package com.example.android_todo.bindingAdapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime

@BindingAdapter("dateTime")
fun TextView.setDateTime(dateTime: Long?) {
    dateTime?.let {
        text = DateTime.fromUnix(dateTime).format("yyyy-MM-dd HH:mm")
    }
}

@BindingAdapter("date")
fun TextView.setDate(date: Long?) {
    date?.let {
        text = DateTime.fromUnix(date).dateDayStart
            .format(DateFormat.FORMAT_DATE)
    }
}

@BindingAdapter("time")
fun TextView.setTime(time: Int?) {
    time?.let {
        val h = time / 60
        val m = time - h * 60
        val template = "%02d"
        text = "${template.format(h)}:${template.format(m)}"
    }
}