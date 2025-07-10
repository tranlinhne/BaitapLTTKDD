package com.yourcompany.speccheckapp.ui.screens // Đảm bảo package name khớp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.speccheckapp.ui.theme.SpecCheckBlue
import com.yourcompany.speccheckapp.ui.theme.SpecCheckTheme
import com.yourcompany.speccheckapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onCloseClick: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu", style = Typography.titleLarge.copy(color = Color.White)) },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Đóng menu",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SpecCheckBlue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            MenuItem(
                title = "Laptop, Macbook",
                subItems = listOf("Lenovo", "ASUS", "Macbook", "Dell", "Acer", "Legion"),
                onItemClick = onMenuItemClick
            )
            MenuItem(
                title = "Linh kiện máy tính",
                subItems = listOf("CPU", "RAM", "SSD", "VGA"), // Ví dụ
                onItemClick = onMenuItemClick
            )
            MenuItem(
                title = "Tản nhiệt",
                subItems = emptyList(), // Ví dụ
                onItemClick = onMenuItemClick
            )
            MenuItem(
                title = "Màn hình, Arm",
                subItems = emptyList(), // Ví dụ
                onItemClick = onMenuItemClick
            )
            MenuItem(
                title = "Gear",
                subItems = emptyList(), // Ví dụ
                onItemClick = onMenuItemClick
            )
        }
    }
}

@Composable
fun MenuItem(
    title: String,
    subItems: List<String>,
    onItemClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = Typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            if (subItems.isNotEmpty()) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                    contentDescription = if (expanded) "Thu gọn" else "Mở rộng",
                    tint = Color.Gray
                )
            }
        }
        if (expanded && subItems.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp) // Thụt vào cho sub-item
            ) {
                subItems.forEach { subItem ->
                    Text(
                        text = subItem,
                        style = Typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(subItem) }
                            .padding(vertical = 10.dp, horizontal = 8.dp)
                    )
                }
            }
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {
    SpecCheckTheme {
        MenuScreen(
            onCloseClick = {},
            onMenuItemClick = {}
        )
    }
}