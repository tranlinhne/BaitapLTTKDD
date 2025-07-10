package com.yourcompany.speccheckapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourcompany.speccheckapp.R // Đảm bảo bạn đã có tệp R.kt được tạo tự động
import com.yourcompany.speccheckapp.ui.theme.SpecCheckBlue
import com.yourcompany.speccheckapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithTitle(
    title: String,
    onBackClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.titleLarge.copy(color = Color.White)
            )
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            onMenuClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SpecCheckBlue
        )
    )
}

@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(60.dp)
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Trang chủ") },
            label = { Text("Trang chủ", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SpecCheckBlue,
                selectedTextColor = SpecCheckBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = onCartClick,
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ hàng") },
            label = { Text("Giỏ hàng", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SpecCheckBlue,
                selectedTextColor = SpecCheckBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = { Icon(Icons.Default.Person, contentDescription = "Cá nhân") },
            label = { Text("Cá nhân", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SpecCheckBlue,
                selectedTextColor = SpecCheckBlue,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}