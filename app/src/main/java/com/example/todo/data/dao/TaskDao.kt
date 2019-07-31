package com.example.todo.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun addTask(task: Task)

    @Query("DELETE FROM task where id= :id")
    fun deleteTask(id: Int)

    @Query("UPDATE task SET completeStatus = :completeStatus where id= :id")
    fun updateTask(id: Int, completeStatus: Boolean)
}