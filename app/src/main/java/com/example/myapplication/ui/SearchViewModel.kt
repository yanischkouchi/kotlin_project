package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.TaskDao
import com.example.myapplication.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SearchViewModel(private val dao: TaskDao) : ViewModel() {
    val tasks: Flow<List<Task>> = dao.getAll()

    fun addTask(title: String) {
        viewModelScope.launch {
            dao.insert(Task(title = title))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            dao.delete(task)
        }
    }
}
