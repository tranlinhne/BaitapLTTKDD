package com.yourcompany.uthsmarttasks.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.uthsmarttasks.data.model.Task
import com.yourcompany.uthsmarttasks.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val repository: TaskRepository = TaskRepository()) : ViewModel() {

    var task: Task? by mutableStateOf(null)
        private set
    var isLoading: Boolean by mutableStateOf(false)
        private set
    var errorMessage: String? by mutableStateOf(null)
        private set

    fun fetchTaskDetail(taskId: Int) {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            val fetchedTask = repository.getTaskDetail(taskId)
            if (fetchedTask != null) {
                task = fetchedTask
            } else {
                errorMessage = "Failed to load task details."
            }
            isLoading = false
        }
    }
}