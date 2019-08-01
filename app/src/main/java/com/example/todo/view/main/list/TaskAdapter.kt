package com.example.todo.view.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.entity.Task
import javax.inject.Inject
import android.content.ClipData.Item

class TaskAdapter @Inject constructor(
        private var taskList: List<Task>,
        private val checkBoxClickListener: View.OnClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val listView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_task, parent, false)
        return TaskListViewHolder(listView)
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task: Task? = taskList[position]
        holder.taskTitle.text = task?.title
        holder.taskDescription.text = task?.description
        holder.taskStatus.isChecked = task?.completeStatus ?: false
        holder.taskStatus.tag = task
        holder.taskStatus.setOnClickListener(checkBoxClickListener)
    }

    inner class TaskListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var taskTitle: TextView = view.findViewById(R.id.textViewTaskTitle)
        var taskDescription: TextView = view.findViewById(R.id.textViewTaskDescription)
        var taskStatus: CheckBox = view.findViewById(R.id.checkBoxTaskStatus)
    }

    fun setItems(tasks: List<Task>) {
        this.taskList = tasks
        notifyDataSetChanged()
    }
}