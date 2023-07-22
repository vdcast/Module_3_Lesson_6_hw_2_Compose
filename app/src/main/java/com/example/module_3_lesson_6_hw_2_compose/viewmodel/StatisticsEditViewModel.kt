package com.example.module_3_lesson_6_hw_2_compose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsItem
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsRepository
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class StatisticsEditViewModel(private val statisticsRepository: StatisticsRepository) : ViewModel() {
    var statisticsItemUiState by mutableStateOf(StatisticsItemUiState())
        private set

    fun updateUiState(statisticsItemDetail: StatisticsItemDetails) {
        statisticsItemUiState = StatisticsItemUiState(statisticsItemDetails = statisticsItemDetail)
    }

    suspend fun saveTest(name: String, newValue: Double) {
        val statistic = statisticsRepository.getRoomByName(name)
        statistic?.let {
            val updatedStatistic = it.copy(value = newValue)
            statisticsRepository.update(updatedStatistic)
        }
    }
}

data class StatisticsItemDetails(
    val id: Int = 0,
    val name: String = "",
    val value: Double = 0.0
)

data class StatisticsItemUiState(
    val statisticsItemDetails: StatisticsItemDetails = StatisticsItemDetails()
)

fun StatisticsItemDetails.toStatisticsItem(): StatisticsItem = StatisticsItem(
    id = id,
    name = name,
    value = value
)

fun StatisticsItem.toStatisticsItemDetails(): StatisticsItemDetails = StatisticsItemDetails(
    id = id,
    name = name,
    value = value
)

fun StatisticsItem.toStatisticsItemUiState(): StatisticsItemUiState = StatisticsItemUiState(
    statisticsItemDetails = this.toStatisticsItemDetails()
)

