package com.example.todo

import android.app.Activity
import android.app.Application
import com.example.todo.injection.AppInjector
import com.example.todo.injection.DaggerTaskComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TaskApplication() : Application(), HasActivityInjector  {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initializeApplication()
    }


    private fun initializeApplication() {
        DaggerTaskComponent.builder().application(this).build().inject(this)
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector    }

}