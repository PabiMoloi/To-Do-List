package com.example.todo.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.data.entity.Task
import com.example.todo.repository.TaskRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import io.reactivex.CompletableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    var taskList: LiveData<List<Task>>? = null
    var numberOfCompletedTasks = MutableLiveData<Int>()

    internal fun addTask(task: Task) {
        return taskRepository.addTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onComplete() {
                        Log.d("Complete: ", "onComplete - task added successfully")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Error:", "onError -:$e")
                    }
                })
    }

    internal fun deleteTask(id: Int) {
        return taskRepository.deleteTask(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onComplete() {
                        Log.d("Complete: ", "onComplete - note successfully deleted")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Error:", "onError -:$e")
                    }
                })
    }

    internal fun getAllTasks(): LiveData<List<Task>> {
        taskList = taskRepository.getAllTasks()
        return taskList as LiveData<List<Task>>
    }

    internal fun updateTask(id: Int, completeStatus: Boolean) {
        return taskRepository.updateTask(id, completeStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onComplete() {
                        Log.d("Complete: ", "onComplete - task updated successfully")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Error:", "onError -:$e")
                    }
                })
    }

    internal fun getCompletedTasks(status: Boolean) {
        numberOfCompletedTasks.value = taskRepository.getCompletedTasks(status)
    }
}