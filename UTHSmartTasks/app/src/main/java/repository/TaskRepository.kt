package com.yourcompany.uthsmarttasks.data.repository
import com.yourcompany.uthsmarttasks.data.model.Task
import com.yourcompany.uthsmarttasks.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository {

    private val apiService = RetrofitClient.taskApiService

    suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getTasks()
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    emptyList() // Trả về danh sách rỗng nếu không thành công hoặc body null
                }
            } catch (e: Exception) {
                e.printStackTrace() // Log lỗi để debug
                emptyList() // Trả về danh sách rỗng khi có lỗi mạng
            }
        }
    }

    suspend fun getTaskDetail(taskId: Int): Task? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getTaskDetail(taskId)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun deleteTask(taskId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteTask(taskId)
                response.isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}