package com.yourcompany.uthsmarttasks.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true) // Cần nếu dùng Moshi code generation (kapt/ksp)
data class Task(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "is_completed") val isCompleted: Boolean,
    @Json(name = "attachment_url") val attachmentUrl: String? = null // Có thể null
)