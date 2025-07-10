package com.yourcompany.speccheckapp.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.speccheckapp.data.Product
import com.yourcompany.speccheckapp.ui.components.BottomNavigationBar
import com.yourcompany.speccheckapp.ui.components.ProductComparisonCard
import com.yourcompany.speccheckapp.ui.components.TopAppBarWithTitle
import com.yourcompany.speccheckapp.ui.theme.SpecCheckTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductComparisonScreen(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAddProductToCompare: () -> Unit
) {
    // Dữ liệu sản phẩm mẫu
    val product1 = Product(
        name = "Laptop Lenovo Gaming LOQ 15ARP8 R7 7435HS (83JCO03YVN)",
        imageUrl = "laptop_lenovo_loq",
        price = "26.190.000đ",
        cpu = "AMD Ryzen 7 -7435HS",
        cores = 8,
        threads = 16,
        ram = "24 GB",
        ssd = "512 GB SSD",
        displaySize = "15.6\"",
        refreshRate = "144Hz",
        battery = "60 Wh"
    )

    Scaffold(
        topBar = {
            TopAppBarWithTitle(
                title = "So sánh sản phẩm",
                onBackClick = onBackClick,
                onMenuClick = onMenuClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onHomeClick = onHomeClick,
                onCartClick = onCartClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProductComparisonCard(
                    product = product1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                ProductComparisonCard(
                    product = null, // Để trống cho sản phẩm thứ hai
                    modifier = Modifier.weight(1f),
                    onAddProductClick = onAddProductToCompare
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductComparisonScreen() {
    SpecCheckTheme {
        ProductComparisonScreen(
            onBackClick = {},
            onMenuClick = {},
            onHomeClick = {},
            onCartClick = {},
            onProfileClick = {},
            onAddProductToCompare = {}
        )
    }
}