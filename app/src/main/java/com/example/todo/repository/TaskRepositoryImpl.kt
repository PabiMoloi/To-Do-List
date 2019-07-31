package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.dao.TaskDao
import com.example.todo.data.entity.Task
import io.reactivex.Completable
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
        private val taskDao: TaskDao
) : TaskRepository {

    override fun addTask(task: Task): Completable {
        return Completable.fromAction { taskDao.addTask(task) }
    }

    override fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun deleteTask(id: Int): Completable {
        return Completable.fromAction { taskDao.deleteTask(id) }
    }

    override fun updateTask(id: Int, completeStatus: Boolean): Completable {
        return Completable.fromAction { taskDao.updateTask(id, completeStatus) }
    }
}