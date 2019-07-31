package com.example.todo.injection

import android.content.Context
import com.example.todo.TaskApplication
import com.example.todo.data.dao.TaskDao
import com.example.todo.data.db.TaskDatabase
import com.example.todo.data.entity.Task
import com.example.todo.repository.TaskRepository
import com.example.todo.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [(AndroidSupportInjectionModule::class),(ViewModelModule::class)])
open class TaskModule {
    @Provides
    @Singleton
    open fun provideContext(application: TaskApplication): Context {
        return application
    }

    @Provides
    @Singleton
    open fun providesTaskDatabase(context: TaskApplication): TaskDatabase {
        return TaskDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    open fun providesTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }

    @Provides
    open fun providesTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}