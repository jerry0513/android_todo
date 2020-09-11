package com.example.android_todo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_todo.ui.todoEdit.TodoEditViewModel
import com.example.android_todo.ui.todoList.TodoListViewModel
import com.example.android_todo.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TodoListViewModel::class)
    abstract fun bindTodoListViewModel(viewModel: TodoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TodoEditViewModel::class)
    abstract fun bindTodoEditViewModel(viewModel: TodoEditViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}