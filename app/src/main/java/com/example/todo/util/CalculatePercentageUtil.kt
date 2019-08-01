package com.example.todo.util

fun returnPercentage(tasksCompleted: Int, numberOfTasks: Int): Int {
    return (tasksCompleted.times(100) / (numberOfTasks - 1))
}