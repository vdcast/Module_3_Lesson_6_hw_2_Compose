package com.example.module_3_lesson_6_hw_2_compose.ui.navigation

sealed class ScreenRoutes(val route: String) {
    object HomeScreen : ScreenRoutes("home_screen")
    object LightScreen : ScreenRoutes("light_screen")
    object LightControlScreen : ScreenRoutes("light_control_screen")
    object LightStatisticsScreen : ScreenRoutes("light_statistics_screen")
    object KitchenScreen : ScreenRoutes("kitchen_screen")
    object TasksScreen : ScreenRoutes("tasks_screen")
    object AirConditioningScreen : ScreenRoutes("air_conditioning_screen")
    object SettingsScreen : ScreenRoutes("settings_screen")
    object LanguageScreen : ScreenRoutes("language_screen")
    object AdvancedSettingsScreen : ScreenRoutes("advanced_settings_screen")
    object MemoryScreen : ScreenRoutes("memory_screen")
}