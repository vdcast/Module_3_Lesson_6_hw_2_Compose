package com.example.module_3_lesson_6_hw_2_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.module_3_lesson_6_hw_2_compose.ui.navigation.SmartHouseManagerNavHost
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green20

@Composable
fun SmartHouseManagerApp(
    navController: NavHostController = rememberNavController()
) {
    MaterialTheme() {
        Box(modifier = Modifier.background(Green20)) {
            SmartHouseManagerNavHost(navController = navController)
        }
    }
}