package com.yourcompany.speccheckapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourcompany.speccheckapp.R // Đảm bảo R.kt được import đúng
import com.yourcompany.speccheckapp.data.Product // Import Product data class
import com.yourcompany.speccheckapp.ui.theme.SpecCheckTheme
import com.yourcompany.speccheckapp.ui.theme.Typography

@Composable
fun ProductComparisonCard(
    product: Product?,
    modifier: Modifier = Modifier,
    onAddProductClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .width(180.dp) // Cố định chiều rộng cho thẻ sản phẩm
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Phần ảnh và nút Add
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Chiều cao cố định cho ảnh/placeholder
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                if (product != null) {
                    Image(
                        painter = painterResource(id = R.drawable.laptop_lenovo_loq),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    // Placeholder khi chưa có sản phẩm
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Thêm sản phẩm",
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { onAddProductClick?.invoke() },
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Vui lòng chọn 1 sản phẩm",
                            style = Typography.bodyMedium.copy(color = Color.Gray),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }

            if (product != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = product.name,
                        style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis

                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.price,
                        style = Typography.headlineSmall.copy(color = Color.Red, fontWeight = FontWeight.Bold)
                    )
                }

                Divider(modifier = Modifier.fillMaxWidth().height(1.dp), color = Color.LightGray)

                // Các thông số kỹ thuật
                SpecificationItem(label = "CPU:", value = product.cpu)
                SpecificationItem(label = "Số nhân:", value = product.cores.toString())
                SpecificationItem(label = "Số luồng:", value = product.threads.toString()) // "Số lượng" -> "Số luồng"
                SpecificationItem(label = "RAM:", value = product.ram)
                SpecificationItem(label = "SSD:", value = product.ssd)
                SpecificationItem(label = "Màn hình:", value = product.displaySize)
                SpecificationItem(label = "Tần số:", value = product.refreshRate)
                SpecificationItem(label = "Pin:", value = product.battery)
            }
        }
    }
}

@Composable
fun SpecificationItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier.width(80.dp) // Cố định chiều rộng cho label
            )
            Text(
                text = value,
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(1f)
            )
        }
        Divider(modifier = Modifier.fillMaxWidth().height(0.5.dp), color = Color.LightGray)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProductComparisonCard() {
    SpecCheckTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProductComparisonCard(
                product = Product(
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
            )
            Spacer(modifier = Modifier.width(16.dp))
            ProductComparisonCard(product = null) // Thẻ trống để so sánh
        }
    }
}