package com.example.android_todo.data

sealed class Result<out T> {
    data class Success<T>(val value : T): Result<T>()
    data class Failed(val message: String): Result<Nothing>()
}