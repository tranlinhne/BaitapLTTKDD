package com.yourcompany.uthsmarttasks.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage // Sử dụng AsyncImage từ Coil
import com.yourcompany.uthsmarttasks.data.model.Task
import com.yourcompany.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.yourcompany.uthsmarttasks.ui.viewmodels.TaskDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    onBackClick: () -> Unit,
    viewModel: TaskDetailViewModel = viewModel()
) {
    val task = viewModel.task
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val context = LocalContext.current

    // Lấy chi tiết task khi màn hình được tạo
    LaunchedEffect(taskId) {
        viewModel.fetchTaskDetail(taskId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (errorMessage != null) {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (task == null) {
                Text(
                    text = "Task not found.",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()), // Cho phép cuộn
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Subtasks (nếu có)
                    Text(
                        text = "Subtasks",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Đây chỉ là mockup, bạn có thể triển khai List<Subtask> nếu API có
                    Text("This task is related to Fitness. It needs to be completed.", style = MaterialTheme.typography.bodyMedium)
                    Text("This task is related to Fitness. It needs to be completed.", style = MaterialTheme.typography.bodyMedium)
                    Text("This task is related to Fitness. It needs to be completed.", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Attachments (nếu có)
                    if (!task.attachmentUrl.isNullOrBlank()) {
                        Text(
                            text = "Attachments",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Sử dụng Coil để hiển thị ảnh từ URL nếu có
                        AsyncImage(
                            model = task.attachmentUrl,
                            contentDescription = "Attachment",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Giả định các file đính kèm chỉ là link, có thể nhấn vào
                        Text(
                            text = task.attachmentUrl, // Hiện URL để mockup
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary, textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline),
                            modifier = Modifier.clickable {
                                // Mở URL trong trình duyệt
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(task.attachmentUrl))
                                context.startActivity(intent)
                            }
                        )
                    } else {
                        Text(
                            text = "No Attachments",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskDetailScreen() {
    UTHSmartTasksTheme {
        TaskDetailScreen(taskId = 1, onBackClick = {})
    }
}