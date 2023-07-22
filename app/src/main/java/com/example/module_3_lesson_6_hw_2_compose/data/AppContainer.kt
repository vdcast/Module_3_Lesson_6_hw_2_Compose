package com.example.module_3_lesson_6_hw_2_compose.data

import android.content.Context
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.OfflineRoomsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.OfflineStatisticsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.statistics.StatisticsRepository

interface AppContainer {
    val roomsRepository: RoomsRepository
    val statisticsRepository: StatisticsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val roomsRepository: RoomsRepository by lazy {
        OfflineRoomsRepository(AppDatabase.getDatabase(context).roomDao())
    }
    override val statisticsRepository: StatisticsRepository by lazy {
        OfflineStatisticsRepository(AppDatabase.getDatabase(context).statisticsDao())
    }

}