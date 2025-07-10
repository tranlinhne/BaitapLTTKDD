package com.yourcompany.uthsmarttasks.ui.viewmodels
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.uthsmarttasks.data.model.Task
import com.yourcompany.uthsmarttasks.data.repository.TaskRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class TaskListViewModel(private val repository: TaskRepository = TaskRepository()) : ViewModel() {

    val tasks = mutableStateListOf<Task>()
    var isLoading: Boolean by androidx.compose.runtime.mutableStateOf(false)
        private set
    var errorMessage: String? by androidx.compose.runtime.mutableStateOf(null)
        private set

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            val fetchedTasks = repository.getTasks()
            tasks.clear()
            tasks.addAll(fetchedTasks)
            isLoading = false
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            val success = repository.deleteTask(taskId)
            if (success) {
                val iterator = tasks.iterator()
                while (iterator.hasNext()) {
                    if (iterator.next().id == taskId) {
                        iterator.remove()
                        break
                    }
                }

            } else {
                errorMessage = "Failed to delete task."
            }
        }
    }
}