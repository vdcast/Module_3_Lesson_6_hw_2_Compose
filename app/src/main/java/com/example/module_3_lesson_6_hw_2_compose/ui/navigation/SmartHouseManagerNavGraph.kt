package com.example.module_3_lesson_6_hw_2_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.module_3_lesson_6_hw_2_compose.ui.air_conditioning.AirConditioningScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.home.HomeScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.kitchen.KitchenScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.light.LightScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.light.light_control.LightControlScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.light.statistics.LightStatisticsScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.settings.SettingsScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.settings.advanced_settings.AdvancedSettingsScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.settings.about.AboutScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.settings.memory.MemoryScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.tasks.TasksScreen

@Composable
fun SmartHouseManagerNavHost(
    navController: NavHostController
) {
    NavHost(
    navController = navController,
    startDestination = ScreenRoutes.HomeScreen.route
    ) {
        composable(ScreenRoutes.HomeScreen.route) {
            HomeScreen(
                onLightClicked = { navController.navigate(ScreenRoutes.LightScreen.route) },
                onKitchenClicked = { navController.navigate(ScreenRoutes.KitchenScreen.route) },
                onTasksClicked = { navController.navigate(ScreenRoutes.TasksScreen.route) },
                onAirConditionClicked = { navController.navigate(ScreenRoutes.AirConditioningScreen.route) },
                onSettingsClicked = { navController.navigate(ScreenRoutes.SettingsScreen.route) }
            )
        }
        composable(ScreenRoutes.LightScreen.route) {
            LightScreen(
                onLightControlClicked = { navController.navigate(ScreenRoutes.LightControlScreen.route) },
                onLightStatisticsClicked = { navController.navigate(ScreenRoutes.LightStatisticsScreen.route) }
            )
        }
        composable(ScreenRoutes.LightControlScreen.route) {
            LightControlScreen()
        }
        composable(ScreenRoutes.LightStatisticsScreen.route) {
            LightStatisticsScreen()
        }
        composable(ScreenRoutes.KitchenScreen.route) {
            KitchenScreen()
        }
        composable(ScreenRoutes.TasksScreen.route) {
            TasksScreen()
        }
        composable(ScreenRoutes.AirConditioningScreen.route) {
            AirConditioningScreen()
        }
        composable(ScreenRoutes.SettingsScreen.route) {
            SettingsScreen(
                onAdvancedSettingsClicked = { navController.navigate(ScreenRoutes.AdvancedSettingsScreen.route) },
                onMemoryClicked = { navController.navigate(ScreenRoutes.MemoryScreen.route) },
                onLanguageClicked = { navController.navigate(ScreenRoutes.AboutScreen.route) }
            )
        }
        composable(ScreenRoutes.AdvancedSettingsScreen.route) {
            AdvancedSettingsScreen()
        }
        composable(ScreenRoutes.MemoryScreen.route) {
            MemoryScreen()
        }
        composable(ScreenRoutes.AboutScreen.route) {
            AboutScreen()
        }
    }
}