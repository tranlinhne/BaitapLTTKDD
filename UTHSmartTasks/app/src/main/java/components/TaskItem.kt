package com.yourcompany.uthsmarttasks.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.yourcompany.uthsmarttasks.R // Icon task complete
import com.yourcompany.uthsmarttasks.data.model.Task
import com.yourcompany.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.yourcompany.uthsmarttasks.ui.theme.Typography

@Composable
fun TaskItem(
    task: Task,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onItemClick(task.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icon Complete/Incomplete
                Image(
                    painter = painterResource(
                        id = if (task.isCompleted) R.drawable.ic_task_completed else R.drawable.ic_task_incomplete
                    ),
                    contentDescription = if (task.isCompleted) "Completed" else "Incomplete",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(if (task.isCompleted) Color.Green.copy(alpha = 0.7f) else Color.Gray)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        style = Typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
                        ),
                        color = if (task.isCompleted) Color.Gray else Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.description,
                        style = Typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,

                contentDescription = "Xem chi tiết",
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskItem() {
    UTHSmartTasksTheme {
        Column {
            TaskItem(
                task = Task(1, "Complete Android Project", "This task is related to Fitness. It needs to be completed", false, null),
                onItemClick = {}
            )
            TaskItem(
                task = Task(2, "Learn more about API", "Understand how to integrate API and write documentation", true, null),
                onItemClick = {}
            )
        }
    }
}