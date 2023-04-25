package com.pico.make_it_so.presentation.home.home.task.task_details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.domain.model.Task
import com.pico.make_it_so.presentation._nav_graphs.TaskNavGraph
import com.pico.make_it_so.presentation.destinations.AddEditScreenDestination
import com.pico.make_it_so.presentation.home.home.task.task_details.components.IconWithLabel
import com.pico.make_it_so.presentation.home.home.task.task_details.components.TaskDetailsTopAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@TaskNavGraph
@Destination
@Composable
fun TaskDetailsScreen(
    navigator: DestinationsNavigator,
    viewModel: TaskDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(
            TaskDetailsEvent.OnTaskChange(
                Task(
                    title = "Finish this stupid application",
                    description = "lorem ipusome some oho so some of th some onf po ;li okd , apsodkf tho asdkof thowe some ",
                    dueDate = "04/04/2023",
                    dueTime = "02:30"
                )
            )
        )
    }
    Scaffold(
        topBar = {
            TaskDetailsTopAppBar(
                navigateUp = { navigator.navigateUp() },
                onEditClick = { navigator.navigate(AddEditScreenDestination) }
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
                    Text(text = description)
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
                            label = dueDate,
                            icon = R.drawable.calendar_month_fill0_wght400_grad0_opsz24
                        )
                        IconWithLabel(
                            label = dueTime,
                            icon = R.drawable.schedule_fill0_wght400_grad0_opsz24
                        )
                        IconWithLabel(
                            label = dueTime,
                            icon = R.drawable.schedule_fill0_wght400_grad0_opsz24
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