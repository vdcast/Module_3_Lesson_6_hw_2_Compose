package com.example.module_3_lesson_6_hw_2_compose.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.module_3_lesson_6_hw_2_compose.SmartHouseManagerApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RoomsAddViewModel(smartHouseManagerApplication().container.roomsRepository)
        }
        initializer {
            RoomListViewModel(smartHouseManagerApplication().container.roomsRepository)
        }
        initializer {
            StatisticsListViewModel(smartHouseManagerApplication().container.statisticsRepository)
        }
        initializer {
            StatisticsEditViewModel(smartHouseManagerApplication().container.statisticsRepository)
        }
        initializer {
            KitchenViewModel(smartHouseManagerApplication().container.kitchenRepository)
        }
        initializer {
            TaskViewModel(smartHouseManagerApplication().container.taskRepository)
        }
    }
}

fun CreationExtras.smartHouseManagerApplication(): SmartHouseManagerApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHouseManagerApplication)