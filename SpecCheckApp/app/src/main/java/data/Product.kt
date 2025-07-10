package com.yourcompany.speccheckapp.data
data class Product(
    val name: String,
    val imageUrl: String, // Tên tài nguyên ảnh (ví dụ: "laptop_lenovo_loq")
    val price: String,
    val cpu: String,
    val cores: Int,
    val threads: Int, // Giả định "Số lượng" trong ảnh là số luồng (threads) CPU
    val ram: String,
    val ssd: String,
    val displaySize: String,
    val refreshRate: String,
    val battery: String
)