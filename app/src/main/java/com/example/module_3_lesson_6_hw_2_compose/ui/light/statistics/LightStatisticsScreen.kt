package com.example.module_3_lesson_6_hw_2_compose.ui.light.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.module_3_lesson_6_hw_2_compose.ui.StatisticsOutlinedTextFiled
import com.example.module_3_lesson_6_hw_2_compose.ui.StatisticsRow
import kotlinx.coroutines.launch

@Composable
fun LightStatisticsScreen(
    viewModelStatisticsList: StatisticsListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    viewModelStatisticsEdit: StatisticsEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val statisticsListUiState by viewModelStatisticsList.statisticsListUiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    var currentMonth by remember { mutableStateOf("") }
    var previousMonth by remember { mutableStateOf("") }
    var currentYear by remember { mutableStateOf("") }
    var isEditingState by remember { mutableStateOf(false) }

    LaunchedEffect(viewModelStatisticsEdit) {
        currentMonth = viewModelStatisticsEdit.getCurrentMonthValue()
        previousMonth = viewModelStatisticsEdit.getPreviousMonthValue()
        currentYear = viewModelStatisticsEdit.getCurrentYearValue()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isEditingState) {
            Text(text = stringResource(id = R.string.statistics))
            LazyColumn() {
                itemsIndexed(statisticsListUiState.statisticsList) { index, item ->
                    StatisticsRow(textTitle = item.name, textValue = item.value.toString())
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { isEditingState = true }
            ) {
                Text(text = stringResource(id = R.string.statistics_adjust))
            }
        } else {
            Text(text = stringResource(id = R.string.statistics_adjust))
            StatisticsOutlinedTextFiled(
                value = currentMonth,
                onValueChange = {
                    currentMonth = it
                    viewModelStatisticsEdit.updateUiState(
                        viewModelStatisticsEdit.statisticsItemUiState.statisticsItemDetails
                            .copy(value = if (it.isEmpty()) 0.0 else currentMonth.toDouble())
                    )
                    coroutineScope.launch {
                        viewModelStatisticsEdit.saveTest(
                            "Current month", if (it.isEmpty()) 0.0 else currentMonth.toDouble()
                        )
                    }
                },
                labelId = R.string.statistics_current_month
            )
            StatisticsOutlinedTextFiled(
                value = previousMonth,
                onValueChange = {
                    previousMonth = it
                    viewModelStatisticsEdit.updateUiState(
                        viewModelStatisticsEdit.statisticsItemUiState.statisticsItemDetails
                            .copy(value = if (it.isEmpty()) 0.0 else previousMonth.toDouble())
                    )
                    coroutineScope.launch {
                        viewModelStatisticsEdit.saveTest(
                            "Previous month", if (it.isEmpty()) 0.0 else previousMonth.toDouble()
                        )
                    }
                },
                labelId = R.string.statistics_previous_month
            )
            StatisticsOutlinedTextFiled(
                value = currentYear,
                onValueChange = {
                    currentYear = it
                    viewModelStatisticsEdit.updateUiState(
                        viewModelStatisticsEdit.statisticsItemUiState.statisticsItemDetails
                            .copy(value = if (it.isEmpty()) 0.0 else currentYear.toDouble())
                    )
                    coroutineScope.launch {
                        viewModelStatisticsEdit.saveTest(
                            "Current year", if (it.isEmpty()) 0.0 else currentYear.toDouble()
                        )
                    }
                },
                labelId = R.string.statistics_current_year
            )
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { isEditingState = false }
            ) { Text(text = stringResource(id = R.string.save)) }
        }
    }
}