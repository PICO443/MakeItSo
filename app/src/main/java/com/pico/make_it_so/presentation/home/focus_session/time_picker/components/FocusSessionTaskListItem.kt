package com.pico.make_it_so.presentation.home.focus_session.time_picker.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pico.make_it_so.domain.model.Task

@Composable
fun FocusSessionTaskListItem(
    task: Task,
    isSelected: Boolean,
    onClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(task) },
        tonalElevation = if (isSelected) 11.dp else 0.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected, onCheckedChange = {}, modifier = Modifier.clip(
                    RoundedCornerShape(100)
                )
            )
            Text(text = task.title, modifier = Modifier.weight(1f))
            if (task.timeSpentInMinutes > 0)
                Text(
                    text = "${task.timeSpentInMinutes} Min spent",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium
                )
        }
    }
}