package com.yourcompany.uthsmarttasks.ui.theme // Đảm bảo package name khớp

import androidx.compose.ui.graphics.Color

// Màu sắc chính (UTH màu đỏ)
val UTHRed = Color(0xFFE53935) // Một sắc thái đỏ
val UTHRedDark = Color(0xFFB71C1C) // Đỏ đậm hơn cho status bar
val UTHRedLight = Color(0xFFFFCDD2) // Đỏ nhạt

val PrimaryColor = UTHRed // Màu chủ đạo của ứng dụng
val SecondaryColor = Color(0xFF64B5F6) // Một màu xanh nhẹ
val TertiaryColor = Color(0xFFEFB8C8)

val LightBackground = Color(0xFFFFFFFF)
val DarkBackground = Color(0xFF1C1B1F)

val LightOnPrimary = Color(0xFFFFFFFF)
val LightOnSecondary = Color(0xFF000000)
val LightOnBackground = Color(0xFF1C1B1F)
val LightOnSurface = Color(0xFF1C1B1F)
val LightOnSurfaceVariant = Color(0xFF49454F) // Cho văn bản phụ, icon xám

// Màu sắc cho chế độ tối (nếu muốn hỗ trợ dark theme)
val DarkPrimary = UTHRedLight
val DarkSecondary = Color(0xFF90CAF9)
val DarkTertiary = Color(0xFFEFB8C8)
val DarkOnPrimary = Color(0xFF000000)
val DarkOnSecondary = Color(0xFF000000)
val DarkOnBackground = Color(0xFFE4E1E6)
val DarkOnSurface = Color(0xFFE4E1E6)
val DarkOnSurfaceVariant = Color(0xFFCBC4D0)