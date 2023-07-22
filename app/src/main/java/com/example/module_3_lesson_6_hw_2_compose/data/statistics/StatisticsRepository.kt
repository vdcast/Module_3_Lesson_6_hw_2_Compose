package com.example.module_3_lesson_6_hw_2_compose.data.statistics

import kotlinx.coroutines.flow.Flow


interface StatisticsRepository {
    fun getAllStatistics(): Flow<List<StatisticsItem>>
    suspend fun insert(statisticsItem: StatisticsItem)
    suspend fun update(statisticsItem: StatisticsItem)
    suspend fun delete(statisticsItem: StatisticsItem)
}