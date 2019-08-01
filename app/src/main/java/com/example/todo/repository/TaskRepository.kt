package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.entity.Task
import io.reactivex.Completable

interface TaskRepository {

    fun addTask(task: Task): Completable
    fun getAllTasks(): LiveData<List<Task>>
    fun deleteTask(id: Int): Completable
    fun updateTask(id: Int, completeStatus: Boolean): Completable
    fun getCompletedTasks(status: Boolean): Int
}