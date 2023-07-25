package com.example.module_3_lesson_6_hw_2_compose.ui.settings.memory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.AppViewModelProvider
import com.example.module_3_lesson_6_hw_2_compose.ui.tasks.TaskViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun MemoryScreen(
    viewModelTask: TaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val tasksNumber by viewModelTask.tasksNumber
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.memory))

        Text(text = stringResource(id = R.string.change_memory))
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModelTask.changeTasksNumberDown()
                    }
                }
            ) { Text(text = stringResource(id = R.string.minus_five)) }
            Text(text = stringResource(id = R.string.tasks_number_max, tasksNumber))
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModelTask.changeTasksNumberUp()
                    }
                }
            ) { Text(text = stringResource(id = R.string.plus_five)) }
        }
    }
}