package com.example.module_3_lesson_6_hw_2_compose.ui.light_rooms_and_statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsItem
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StatisticsListViewModel(private val statisticsRepository: StatisticsRepository) : ViewModel() {
    val statisticsListUiState: StateFlow<StatisticsListUiState> =
        statisticsRepository.getAllStatistics().map { StatisticsListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StatisticsListUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class StatisticsListUiState(
    val statisticsList: List<StatisticsItem> = listOf()
)