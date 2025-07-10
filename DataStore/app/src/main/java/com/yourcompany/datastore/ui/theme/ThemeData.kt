package com.yourcompany.datastore.ui.theme

class ThemeData {
}
package com.example.themeappcompose // Thay thế bằng package name của bạn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themeappcompose.ui.theme.ThemeAppComposeTheme // Thay thế bằng theme package của bạn

// --- CÁC MÀU MẪU ĐỂ CHỌN ---
val LightBlue = Color(0xFFADD8E6) // Light Blue
val Pink = Color(0xFFFFC0CB)     // Pink
val Purple = Color(0xFFC8A2C8)   // Lavender (tông tím nhạt)
val DarkGrey = Color(0xFF424242) // Dark Grey cho Dark theme

// Data class để định nghĩa một tùy chọn Theme
data class ThemeOption(
    val name: String,
    val primaryColor: Color,
    val secondaryColor: Color,
    val isDarkTheme: Boolean = false // Để phân biệt theme sáng/tối
)

// Danh sách các tùy chọn theme có sẵn
val themeOptions = listOf(
    ThemeOption("Default", LightBlue, Pink),
    ThemeOption("Pink Mode", Pink, LightBlue),
    ThemeOption("Purple Haze", Purple, Color.Gray), // Màu giả định cho nút thứ 3
    ThemeOption("Dark Mode", DarkGrey, Color.LightGray, isDarkTheme = true)
)

// --- Bắt đầu MainActivity và NavHost ---
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeAppComposeTheme { // Sử dụng theme mặc định của dự án
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController() // Khởi tạo NavController
                    AppNavHost(navController = navController) // Gọi Composable chứa hệ thống điều hướng
                }
            }
        }
    }
}