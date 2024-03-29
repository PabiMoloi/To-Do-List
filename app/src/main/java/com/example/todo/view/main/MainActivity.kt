package com.example.todo.view.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.entity.Task
import com.example.todo.util.returnPercentage
import com.example.todo.view.main.add.AddTaskFragment
import com.example.todo.view.main.list.TaskAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TaskViewModel
    private lateinit var taskListRecyclerView: RecyclerView
    private lateinit var checkBoxOnClickListener: View.OnClickListener
    private lateinit var deleteTaskButton: View.OnClickListener
    private lateinit var listAdapter: TaskAdapter
    private var addTaskBottomSheetDialogFragment = AddTaskFragment()
    private var percentageComplete: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupViews()
        listAdapter = TaskAdapter(ArrayList(), checkBoxOnClickListener, deleteTaskButton)
        setupRecyclerView()
        startObservers()
    }

    private fun setupViews() {
        toolbar.title = resources.getString(R.string.title_to_do_list)
        floatingActionButtonAddTask.setOnClickListener {
            addTaskBottomSheetDialogFragment.show(supportFragmentManager, "bottomsheet")
        }
        checkBoxOnClickListener = View.OnClickListener {
            val task = it.tag as Task
            if (task.completeStatus!!) {
                task.id?.let { it1 -> viewModel.updateTask(it1, false) }
            } else {
                task.id?.let { it1 -> viewModel.updateTask(it1, true) }
            }
            viewModel.getCompletedTasks(true)
        }
        deleteTaskButton = View.OnClickListener {
            val task = it.tag as Task
            task.id?.let { it1 -> viewModel.deleteTask(it1) }
            viewModel.getCompletedTasks(true)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TaskViewModel::class.java]
    }

    private fun startObservers() {
        viewModel.getAllTasks().observe(this, Observer {
            viewModel.taskList?.value?.toList()?.let { it1 -> listAdapter.setItems(it1) }
            viewModel.getCompletedTasks(true)
        })

        viewModel.numberOfCompletedTasks.observe(this, Observer {
            percentageComplete = returnPercentage(it, listAdapter.itemCount)
            textViewPercentageDone.text = resources.getString(R.string.title_done, percentageComplete)
            setProgressBarValue(percentageComplete)
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        taskListRecyclerView = recyclerViewTaskList
        taskListRecyclerView.layoutManager = layoutManager
        taskListRecyclerView.adapter = listAdapter
        taskListRecyclerView.isNestedScrollingEnabled = true
        taskListRecyclerView.isFocusable = false
    }

    private fun setProgressBarValue(percentage: Int) {
        progressBar.progress = percentage
    }
}
