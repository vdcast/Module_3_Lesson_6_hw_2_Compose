package com.example.module_3_lesson_6_hw_2_compose.data.statistics

import kotlinx.coroutines.flow.Flow

class OfflineStatisticsRepository(private val statisticsDao: StatisticsDao) : StatisticsRepository {
    override fun getAllStatistics(): Flow<List<StatisticsItem>> = statisticsDao.getAllStatistics()

    override suspend fun insert(statisticsItem: StatisticsItem) = statisticsDao.insert(statisticsItem)

    override suspend fun update(statisticsItem: StatisticsItem) = statisticsDao.update(statisticsItem)

    override suspend fun delete(statisticsItem: StatisticsItem) = statisticsDao.delete(statisticsItem)
}