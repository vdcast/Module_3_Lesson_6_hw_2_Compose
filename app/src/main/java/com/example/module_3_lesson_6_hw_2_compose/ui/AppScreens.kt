package com.example.module_3_lesson_6_hw_2_compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green10
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Module_3_Lesson_6_hw_2_ComposeTheme

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.MainScreen.route
    ) {
        composable(ScreenRoutes.MainScreen.route) {
            MainScreen(
                onLightClicked = { navController.navigate(ScreenRoutes.LightScreen.route) },
                onKitchenClicked = { navController.navigate(ScreenRoutes.KitchenScreen.route) },
                onTasksClicked = { navController.navigate(ScreenRoutes.TasksScreen.route) },
                onAirConditionClicked = { navController.navigate(ScreenRoutes.AirConditioningScreen.route) }
            )
        }
        composable(ScreenRoutes.LightScreen.route) {
            LightScreen()
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

@Composable
fun MainScreen(
    onLightClicked: () -> Unit,
    onKitchenClicked: () -> Unit,
    onTasksClicked: () -> Unit,
    onAirConditionClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.smart_house_manager))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = onLightClicked
        ) {
            Text(text = stringResource(id = R.string.light))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = onKitchenClicked
        ) {
            Text(text = stringResource(id = R.string.kitchen))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = onTasksClicked
        ) {
            Text(text = stringResource(id = R.string.tasks))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = onAirConditionClicked
        ) {
            Text(text = stringResource(id = R.string.air_conditioning))
        }
    }
}

@Composable
fun LightScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.light))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.light_control))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.statistics))
        }
    }
}

@Composable
fun KitchenScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.kitchen))
        Text(text = "Cooking started?")
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.start_cooking))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.tasks))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(id = R.dimen.padding_xsmall),
                    horizontal = dimensionResource(id = R.dimen.padding_small)
                )
                .height(dimensionResource(id = R.dimen.padding_xxlarge)),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_medium)),
            elevation = CardDefaults.cardElevation(
                dimensionResource(id = R.dimen.padding_xsmall)
            ),
            colors = CardDefaults.cardColors(Green10),
            onClick = { }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "some task",
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = dimensionResource(id = R.dimen.padding_medium))
                )
                Text(
                    text = "some task",
                    color = Color.White,
                    modifier = Modifier
                        .weight(0.5f)
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.add_task))
        }
    }
}

@Composable
fun AirConditioningScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.air_conditioning))
        Text(text = stringResource(id = R.string.current_temperature, 28))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.temperature_up))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.temperature_down))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Module_3_Lesson_6_hw_2_ComposeTheme {
        AirConditioningScreen()
    }
}