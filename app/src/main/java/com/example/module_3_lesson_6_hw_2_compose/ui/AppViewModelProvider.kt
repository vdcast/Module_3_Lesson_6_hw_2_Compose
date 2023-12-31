package com.example.module_3_lesson_6_hw_2_compose.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.module_3_lesson_6_hw_2_compose.SmartHouseManagerApplication
import com.example.module_3_lesson_6_hw_2_compose.ui.air_conditioning.AirConditioningViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.kitchen.KitchenViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.light.light_control.RoomListViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.light.light_control.RoomsAddViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.light.statistics.StatisticsEditViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.light.statistics.StatisticsListViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.tasks.TaskViewModel

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
            TaskViewModel(
                smartHouseManagerApplication().container.taskRepository,
                smartHouseManagerApplication().container.settingsRepository
            )
        }
        initializer {
            AirConditioningViewModel(smartHouseManagerApplication().container.airConditioningRepository)
        }
    }
}

fun CreationExtras.smartHouseManagerApplication(): SmartHouseManagerApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SmartHouseManagerApplication)