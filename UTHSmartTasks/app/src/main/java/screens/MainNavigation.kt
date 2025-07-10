package com.yourcompany.uthsmarttasks.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.material3.Text


@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "task_list") {
        composable("task_list") {
            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate("task_detail/$taskId")
                }
            )
        }
        composable(
            "task_detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId")
            if (taskId != null) {
                TaskDetailScreen(
                    taskId = taskId,
                    onBackClick = { navController.popBackStack() }
                )
            } else {
                // Xử lý trường hợp taskId null, có thể hiển thị lỗi hoặc quay lại
                Text("Error: Task ID not found")
            }
        }
    }
}