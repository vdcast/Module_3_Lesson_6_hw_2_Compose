package com.example.module_3_lesson_6_hw_2_compose.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.AppViewModelProvider
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green10
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green30
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    viewModelTask: TaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Box(modifier = Modifier.fillMaxSize()) {
        var isAdding by remember{ mutableStateOf(false) }
        val taskListUiState by viewModelTask.taskListUiState.collectAsState()
        var deleteAlertDialog by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()

        val tasksNumber by viewModelTask.tasksNumber

        if (!isAdding) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.padding_m_l),
                                vertical = dimensionResource(id = R.dimen.padding_small)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(
                            id = R.string.tasks_number,
                            taskListUiState.taskList.size,
                            tasksNumber)
                        )
                        Text(
                            modifier = Modifier
                                .padding(all = dimensionResource(id = R.dimen.padding_small))
                                .offset(x = (-16).dp),
                            text = stringResource(id = R.string.tasks),
                            fontSize = 20.sp
                        )
                        if (taskListUiState.taskList.isNotEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .scale(1.25f)
                                    .clickable { deleteAlertDialog = true },
                                imageVector = Icons.Default.Delete,
                                contentDescription = "clear all tasks"
                            )
                        } else {
                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                        }
                    }
                }
                if (taskListUiState.taskList.isEmpty()) {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(all = dimensionResource(id = R.dimen.padding_medium)),
                            text = stringResource(id = R.string.task_list_empty),
                            fontSize = 16.sp
                        )
                    }
                } else {
                    itemsIndexed(taskListUiState.taskList) { index, item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    min = dimensionResource(id = R.dimen.padding_xxlarge),
                                    max = dimensionResource(id = R.dimen.padding_xxxlarge)
                                )
                                .padding(
                                    vertical = dimensionResource(id = R.dimen.padding_xsmall),
                                    horizontal = dimensionResource(id = R.dimen.padding_small)
                                )
                                .verticalScroll(rememberScrollState()),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_medium)),
                            elevation = CardDefaults.cardElevation(
                                dimensionResource(id = R.dimen.padding_xsmall)
                            ),
                            colors = CardDefaults.cardColors(Green30),
                            onClick = { }
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    vertical = dimensionResource(id = R.dimen.padding_medium),
                                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                                )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(
                                            start = dimensionResource(id = R.dimen.padding_xsmall),
                                            bottom = dimensionResource(id = R.dimen.padding_small)
                                        ),
                                    text = item.taskTitle,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                Text(
                                    modifier = Modifier,
                                    text = item.taskText,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                containerColor = Green10,
                contentColor = Color.White,
                shape = CircleShape,
                onClick = {
                    if (viewModelTask.checkTaskMaximumNumber()) {
                        isAdding = true
                    }
                }
            ) { Icon(imageVector = Icons.Default.Add, contentDescription = "add task") }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var taskTitle by remember { mutableStateOf("") }
                var taskText by remember { mutableStateOf("") }
                var isTitleEmpty by remember { mutableStateOf(false) }
                var isTextEmpty by remember { mutableStateOf(false) }



                val focusManager = LocalFocusManager.current

                Text(text = stringResource(id = R.string.add_task))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = taskTitle,
                    onValueChange = {
                        taskTitle = it

                        viewModelTask.updateTaskUiState(
                            viewModelTask.taskAddItemUiState.taskItemDetails.copy(taskTitle = taskTitle)
                        )

                        isTitleEmpty = it.isEmpty()
                    },
                    isError = isTitleEmpty,
                    label = { Text(stringResource(id = R.string.title)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = taskText,
                    onValueChange = {
                        taskText = it
                        viewModelTask.updateTaskUiState(
                            viewModelTask.taskAddItemUiState.taskItemDetails.copy(taskText = taskText)
                        )
                        isTextEmpty = it.isEmpty()
                    },
                    isError = isTextEmpty,
                    label = { Text(stringResource(id = R.string.text)) },
                    maxLines = 10,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    enabled = (taskTitle.isNotEmpty() && taskText.isNotEmpty()),
                    onClick = {
                        coroutineScope.launch {
                            viewModelTask.saveTask()
                            isAdding = false
                        }
                    }
                ) { Text(text = stringResource(id = R.string.save)) }
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    onClick = {
                        isAdding = false
                    }
                ) { Text(text = stringResource(id = R.string.cancel)) }
            }
        }

        if (deleteAlertDialog) {
            AlertDialog(
                onDismissRequest = {  },
                title = { Text(text = stringResource(id = R.string.delete_all_title)) },
                text = { Text( stringResource(id = R.string.delete_all_body) ) },
                confirmButton = {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModelTask.deleteAll()
                                deleteAlertDialog = false
                            }
                        }
                    ) { Text(stringResource(id = R.string.delete_all_button_yes)) }
                },
                dismissButton = {
                    Button(
                        onClick = { deleteAlertDialog = false }
                    ) { Text(stringResource(id = R.string.delete_all_button_no)) }
                }
            )
        }
    }
}