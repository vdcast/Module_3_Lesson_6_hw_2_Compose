package com.example.module_3_lesson_6_hw_2_compose.data

import android.content.Context

interface AppContainer {
    val roomsRepository: RoomsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val roomsRepository: RoomsRepository by lazy {
        OfflineRoomsRepository(AppDatabase.getDatabase(context).roomDao())
    }

}