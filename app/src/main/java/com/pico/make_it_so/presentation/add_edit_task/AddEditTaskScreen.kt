package com.pico.make_it_so.presentation.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation._nav_graphs.TaskNavigationGraph
import com.pico.make_it_so.presentation.add_edit_task.components.AddEditTaskTopAppBar
import com.pico.make_it_so.presentation.add_edit_task.components.Option
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@TaskNavigationGraph(start = true)
@Destination
@Composable
fun AddEditScreen(
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState = viewModel.uiState
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddEditTaskEvent.SaveTask); navigator.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.save_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            }
        },
        topBar = {
            AddEditTaskTopAppBar(navigateBack = { navigator.navigateUp() })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextField(
                value = uiState.taskTitle,
                placeholder = { Text(text = "Title") },
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.TitleChanged(it)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.taskDescription,
                placeholder = { Text(text = "Description") },
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.DescriptionChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            Option(
                label = "Date",
                supportingText = "2023/04/04",
                icon = ImageVector.vectorResource(id = R.drawable.calendar_month_fill0_wght400_grad0_opsz24),
                onButtonClicked = { /*TODO*/ })
            Option(
                label = "Duration",
                supportingText = "02:00",
                icon = ImageVector.vectorResource(id = R.drawable.schedule_fill0_wght400_grad0_opsz24),
                onButtonClicked = { /*TODO*/ })
        }

    }
}
