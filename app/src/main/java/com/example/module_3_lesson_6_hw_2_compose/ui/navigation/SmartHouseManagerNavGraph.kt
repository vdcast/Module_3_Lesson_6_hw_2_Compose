package com.example.module_3_lesson_6_hw_2_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.module_3_lesson_6_hw_2_compose.ui.air_conditioning.AirConditioningScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.home.HomeScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.kitchen.KitchenScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.light_rooms_and_statistics.LightControlScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.light_rooms_and_statistics.LightScreen
import com.example.module_3_lesson_6_hw_2_compose.ui.light_rooms_and_statistics.LightStatisticsScreen
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
                onAirConditionClicked = { navController.navigate(ScreenRoutes.AirConditioningScreen.route) }
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
    }
}