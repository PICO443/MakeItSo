package com.pico.make_it_so.presentation.home.home.task.task_details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.core.getDate
import com.pico.make_it_so.presentation._nav_graphs.TaskNavGraph
import com.pico.make_it_so.presentation.destinations.AddEditScreenDestination
import com.pico.make_it_so.presentation.home.home.task.task_details.components.IconWithLabel
import com.pico.make_it_so.presentation.home.home.task.task_details.components.TaskDetailsTopAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@TaskNavGraph
@Destination(
    navArgsDelegate = TaskDetailsNavArgs::class
)
@Composable
fun TaskDetailsScreen(
    navigator: DestinationsNavigator,
    viewModel: TaskDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TaskDetailsTopAppBar(
                navigateUp = { navigator.navigateUp() },
                onEditClick = { navigator.navigate(AddEditScreenDestination(uiState.task)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            uiState.task.apply {
                Column {
                    Text(text = title, style = MaterialTheme.typography.titleLarge)
                    description?.let {
                        Text(text = it)
                    }
                }
                Surface(tonalElevation = 3.dp, shape = MaterialTheme.shapes.medium) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconWithLabel(
                            label = getDate(dueDate.toDate().time),
                            icon = R.drawable.calendar_month_fill0_wght400_grad0_opsz24
                        )
                        IconWithLabel(
                            label = "$timeSpentInMinutes Minutes",
                            icon = R.drawable.timer_fill0_wght400_grad0_opsz24
                        )
                        IconWithLabel(
                            label = if (completed) "Completed" else "Not completed",
                            icon = if (completed) R.drawable.check_circle_fill1_wght400_grad0_opsz24 else R.drawable.check_circle_fill0_wght400_grad0_opsz24,
                            color = if (completed) Color.Green else LocalContentColor.current
                        )
                    }
                }
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Select for Focus Session")
            }
        }
    }
}