package com.yourcompany.uthsmarttasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yourcompany.uthsmarttasks.ui.components.EmptyView
import com.yourcompany.uthsmarttasks.ui.components.TaskItem
import com.yourcompany.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.yourcompany.uthsmarttasks.ui.viewmodels.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onTaskClick: (Int) -> Unit,
    viewModel: TaskListViewModel = viewModel() // Compose sẽ tự động tạo/tìm ViewModel
) {
    val tasks = viewModel.tasks
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    // Lấy dữ liệu khi màn hình được tạo lần đầu
    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("List", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
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
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            } else if (tasks.isEmpty()) {
                EmptyView(message = "No Tasks Yet!", modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 8.dp) // Khoảng cách cuối danh sách
                ) {
                    items(tasks, key = { it.id }) { task ->
                        TaskItem(task = task, onItemClick = onTaskClick)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskListScreen() {
    UTHSmartTasksTheme {
        TaskListScreen(onTaskClick = {})
    }
}