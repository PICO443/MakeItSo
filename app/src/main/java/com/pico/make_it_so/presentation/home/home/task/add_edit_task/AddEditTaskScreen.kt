package com.pico.make_it_so.presentation.home.home.task.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.core.getDate
import com.pico.make_it_so.presentation._nav_graphs.TaskNavGraph
import com.pico.make_it_so.presentation.destinations.HomeScreenDestination
import com.pico.make_it_so.presentation.home.home.task.add_edit_task.components.AddEditTaskTopAppBar
import com.pico.make_it_so.presentation.home.home.task.add_edit_task.components.IconButtonOption
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@TaskNavGraph(start = true)
@Destination(
    navArgsDelegate = AddEditTaskNavArgs::class
)
@Composable
fun AddEditScreen(
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val uiState = viewModel.uiState
    val focusManager = LocalFocusManager.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTaskEvent.SaveTask(onSuccess = {
                    navigator.navigate(HomeScreenDestination) {
                        popUpTo(HomeScreenDestination.route) { inclusive = true }
                    }
                }))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.save_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            }
        },
        topBar = {
            AddEditTaskTopAppBar(
                navigateBack = { navigator.navigateUp() },
                isEditing = uiState.editing
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            var openDatePicker by remember {
                mutableStateOf(false)
            }
            val datePickerState = rememberDatePickerState(uiState.taskTimestamp.toDate().time)

            if (openDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        openDatePicker = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            openDatePicker = false
                            datePickerState.selectedDateMillis?.let {
                                viewModel.onEvent(
                                    AddEditTaskEvent.OnTaskDateChange(it)
                                )
                            }
                        }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            openDatePicker = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
            TextField(
                value = uiState.taskTitle,
                placeholder = { Text(text = "Title") },
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.OnTitleChange(it))
                },
                singleLine = true,
                isError = uiState.hasTaskTitleError,
                supportingText = { if (uiState.hasTaskTitleError) Text(text = "Title can't be empty !") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.taskDescription ?: "",
                placeholder = { Text(text = "Description") },
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.OnDescriptionChange(it))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    openDatePicker = true
                }),
                modifier = Modifier.fillMaxWidth()
            )
            IconButtonOption(
                label = "Date",
                supportingText = getDate(uiState.taskTimestamp.toDate().time),
                icon = ImageVector.vectorResource(id = R.drawable.calendar_month_fill0_wght400_grad0_opsz24),
                onButtonClicked = {
                    openDatePicker = true
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreenPreview() {
    MakeItSoTheme {
        var openDialog by remember {
            mutableStateOf(true)
        }
        val datePickerState = rememberDatePickerState()
        if (openDialog)
            DatePickerDialog(onDismissRequest = {
                openDialog = false
            }, confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                    println("${datePickerState.selectedDateMillis}")
                }) {
                    Text(text = "OK")
                }
            }) {
                DatePicker(state = datePickerState)
            }
    }
}
