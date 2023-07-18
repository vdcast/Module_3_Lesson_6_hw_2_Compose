package com.example.module_3_lesson_6_hw_2_compose.ui

sealed class ScreenRoutes(val route: String) {
    object MainScreen : ScreenRoutes("main_screen")
    object LightScreen : ScreenRoutes("light_screen")
    object KitchenScreen : ScreenRoutes("kitchen_screen")
    object TasksScreen : ScreenRoutes("tasks_screen")
    object AirConditioningScreen : ScreenRoutes("air_conditioning_screen")
}