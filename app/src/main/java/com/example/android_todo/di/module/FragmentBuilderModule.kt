package com.example.android_todo.di.module

import com.example.android_todo.ui.todoList.TodoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    fun todoListFragment(): TodoListFragment
}