package com.yourcompany.uthsmarttasks.data.remote

import com.yourcompany.uthsmarttasks.data.model.Task
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): Response<List<Task>>

    @GET("tasks/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: Int): Response<Task>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") taskId: Int): Response<Unit> // Unit cho response rỗng
}