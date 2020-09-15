package com.example.android_todo.di.module

import android.app.Application
import android.content.Context
import com.example.android_todo.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApp(app: App): Application

    @Binds
    abstract fun bindContext(app: App): Context
}
