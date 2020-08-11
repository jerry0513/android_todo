package com.example.android_todo.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.android_todo.App
import com.example.android_todo.di.component.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

class AppInjector {
    companion object {
        fun init(app: App) {
            DaggerAppComponent.builder()
                .application(app)
                .build()
                .inject(app)

            app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(p0: Activity) {}
                override fun onActivityStarted(p0: Activity) {}
                override fun onActivityDestroyed(p0: Activity) {}
                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
                override fun onActivityStopped(p0: Activity) {}
                override fun onActivityCreated(p0: Activity, p1: Bundle?) = handleActivity(p0)
                override fun onActivityResumed(p0: Activity) {}
            })
        }

        private fun handleActivity(activity: Activity) {
            if (activity is HasAndroidInjector || activity is Injectable)
                AndroidInjection.inject(activity)

            if (activity is FragmentActivity)
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentAttached(
                            fm: FragmentManager,
                            f: Fragment,
                            context: Context
                        ) {
                            if (f is Injectable)
                                AndroidSupportInjection.inject(f)
                        }
                    }, true
                )
        }
    }
}
