package com.example.todo.injection

import com.example.todo.TaskApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (TaskModule::class), (ActivityBuilderModule::class)])
interface TaskComponent {

    fun inject(taskApplication: TaskApplication)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: TaskApplication): Builder

        fun build(): TaskComponent

        fun taskModule(taskModule: TaskModule): Builder
    }
}