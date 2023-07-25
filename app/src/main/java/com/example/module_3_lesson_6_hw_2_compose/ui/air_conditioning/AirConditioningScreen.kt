package com.example.module_3_lesson_6_hw_2_compose.ui.air_conditioning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun AirConditioningScreen(
    viewModelAc: AirConditioningViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val acListUiState by viewModelAc.acListUiState.collectAsState()
    var currentTemperature by remember { mutableStateOf(0) }
    var stepTemperature by remember { mutableStateOf(0) }
    var acName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    acListUiState.acList.firstOrNull()?.let { item ->
        currentTemperature = item.currentTemperature
        stepTemperature = item.stepTemperature
        acName = item.name
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.air_conditioning))
        Text(text = stringResource(id = R.string.current_temperature, currentTemperature))
        Text(text = stringResource(id = R.string.step_temperature, stepTemperature))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelAc.temperatureUp(acName)
                }
            }
        ) {
            Text(text = stringResource(id = R.string.temperature_up))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelAc.temperatureDown(acName)
                }
            }
        ) {
            Text(text = stringResource(id = R.string.temperature_down))
        }
    }
}