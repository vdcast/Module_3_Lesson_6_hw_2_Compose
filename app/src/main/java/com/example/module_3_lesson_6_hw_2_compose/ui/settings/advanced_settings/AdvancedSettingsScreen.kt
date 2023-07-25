package com.example.module_3_lesson_6_hw_2_compose.ui.settings.advanced_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.module_3_lesson_6_hw_2_compose.ui.air_conditioning.AirConditioningViewModel
import kotlinx.coroutines.launch

@Composable
fun AdvancedSettingsScreen(
    viewModelAc: AirConditioningViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val acListUiState by viewModelAc.acListUiState.collectAsState()
    var stepTemperature by remember { mutableStateOf(0) }
    var acName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    acListUiState.acList.firstOrNull()?.let { item ->
        stepTemperature = item.stepTemperature
        acName = item.name
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.advanced_settings))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {  }
        ) { Text(text = stringResource(id = R.string.change_cooking_time)) }

        Text(text = stringResource(id = R.string.change_ac_step))
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModelAc.changeTemperatureStepDown(acName)
                    }
                }
            ) { Text(text = stringResource(id = R.string.minus)) }
            Text(text = stringResource(id = R.string.degrees_celsius, stepTemperature))
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModelAc.changeTemperatureStepUp(acName)
                    }
                }
            ) { Text(text = stringResource(id = R.string.plus)) }

        }
    }
}