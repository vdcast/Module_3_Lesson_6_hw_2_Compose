package com.example.module_3_lesson_6_hw_2_compose.ui.kitchen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.AppViewModelProvider
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
fun KitchenScreen(
    viewModelKitchen: KitchenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val kitchenUiState by viewModelKitchen.kitchenListUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.kitchen))
        Text(text = stringResource(id = R.string.cooking_status))

        kitchenUiState.kitchenList.firstOrNull()?.let { item ->
            val currentTime = System.currentTimeMillis()
            val startedTime = SimpleDateFormat("HH:mm:ss").format(item.startTime)
            val finishedTime = SimpleDateFormat("HH:mm:ss").format(item.finishTime)
            Text(
                text = when {
                    item.cookingStatus -> stringResource(id = R.string.cooking_status_started)
                    item.finishTime > 0L -> stringResource(id = R.string.cooking_status_finished, finishedTime)
                    else -> stringResource(id = R.string.cooking_status_nothing)
                }
            )
            if (item.cookingStatus) {
                Text(text = stringResource(id = R.string.cooking_started, startedTime))
                Text(text = stringResource(id = R.string.cooking_finished, finishedTime))
                if (currentTime > item.finishTime) {
                    coroutineScope.launch {
                        viewModelKitchen.stopCooking()
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelKitchen.startCooking()
                }
            }
        ) { Text(text = stringResource(id = R.string.start_cooking)) }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelKitchen.stopCookingByButton()
                }
            }
        ) { Text(text = stringResource(id = R.string.stop_cooking)) }
    }
}