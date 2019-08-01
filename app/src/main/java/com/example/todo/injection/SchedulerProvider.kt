package com.example.todo.injection

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun uiScheduler(): Scheduler
    fun ioScheduler(): Scheduler
}