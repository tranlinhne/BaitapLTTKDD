package com.yourcompany.speccheckapp

import android.os.Bundle
import androidx.compose.material3.Text

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourcompany.speccheckapp.ui.screens.MenuScreen
import com.yourcompany.speccheckapp.ui.screens.ProductComparisonScreen
import com.yourcompany.speccheckapp.ui.theme.SpecCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpecCheckTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "product_comparison") {
        composable("product_comparison") {
            ProductComparisonScreen(
                onBackClick = {
                    // Xử lý quay lại: nếu có thể quay lại màn hình trước trong navigation stack
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    } else {
                        // Nếu không có màn hình trước, bạn có thể đóng Activity
                        // val activity = it.lifecycleOwner as? ComponentActivity
                        // activity?.finish()
                    }
                },
                onMenuClick = {
                    navController.navigate("menu_screen")
                },
                onHomeClick = {
                    navController.navigate("home_screen") {
                        // Đảm bảo chỉ có một instance của home screen trong back stack
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onCartClick = {
                    navController.navigate("cart_screen")
                },
                onProfileClick = {
                    navController.navigate("profile_screen")
                },
                onAddProductToCompare = {
                    navController.navigate("select_product_screen")
                }
            )
        }
        composable("menu_screen") {
            MenuScreen(
                onCloseClick = {
                    navController.popBackStack() // Đóng menu
                },
                onMenuItemClick = { item ->
                    // Xử lý khi chọn một mục trong menu, ví dụ: điều hướng đến màn hình danh sách sản phẩm theo hãng
                    // navController.navigate("product_list_screen/$item")
                    navController.popBackStack() // Đóng menu sau khi chọn
                }
            )
        }
        // Màn hình placeholder cho các mục điều hướng khác
        composable("home_screen") { Text("Trang Chủ - (Đang phát triển)") }
        composable("cart_screen") { Text("Giỏ Hàng - (Đang phát triển)") }
        composable("profile_screen") { Text("Cá Nhân - (Đang phát triển)") }
        composable("select_product_screen") { Text("Chọn Sản Phẩm - (Đang phát triển)") }
    }
}