package com.example.android_todo.di.component

import com.example.android_todo.App
import com.example.android_todo.di.module.ActivityBuilderModule
import com.example.android_todo.di.module.DataSourceModule
import com.example.android_todo.di.module.UseCaseModule
import com.example.android_todo.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        DataSourceModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}