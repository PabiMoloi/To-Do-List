package com.example.todo.util

private var percentage: Int = 0

fun returnPercentage(tasksCompleted: Int, numberOfTasks: Int): Int {
    if (numberOfTasks != 0) {
        percentage = (tasksCompleted.times(100) / (numberOfTasks))
    }
    return percentage
}