package com.pico.make_it_so.presentation.home.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pico.make_it_so.R
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.ui.theme.MakeItSoTheme

@Composable
fun TaskListItem(
    task: Task,
    onTaskCheck: (Boolean) -> Unit,
    onClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable(onClick = { onClick(task) }),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.completed, onCheckedChange = onTaskCheck)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
                )
                task.description?.let {
                    Text(text = it, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (task.timeSpentInMinutes > 0) {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                        Text(
                            text = "${task.timeSpentInMinutes} Min spent",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.more_vert_fill0_wght400_grad0_opsz24),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskListItemPreview() {
    MakeItSoTheme() {
        TaskListItem(task = Task(title = "Important Task"), onTaskCheck = {}, onClick = {})
    }
}