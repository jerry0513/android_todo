package com.example.android_todo.bindingAdapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android_todo.R
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime

@BindingAdapter("dateTime")
fun TextView.setDateTime(dateTime: Long?) {
    dateTime?.let {
        text = DateTime.fromUnix(it).format("yyyy-MM-dd HH:mm")
    }
}

@BindingAdapter("date")
fun TextView.setDate(date: Long?) {
    date?.let {
        text = DateTime.fromUnix(it).dateDayStart
            .format(DateFormat.FORMAT_DATE)
    }
}

@BindingAdapter("time")
fun TextView.setTime(time: Int?) {
    time?.let {
        val h = it / 60
        val m = it % 60
        text = resources.getString(R.string.time_format, h, m)
    }
}
