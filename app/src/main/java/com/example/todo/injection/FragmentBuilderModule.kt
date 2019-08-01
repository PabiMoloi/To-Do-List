package com.example.todo.injection

import com.example.todo.view.main.add.AddTaskFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun addTaskFragment(): AddTaskFragment
}