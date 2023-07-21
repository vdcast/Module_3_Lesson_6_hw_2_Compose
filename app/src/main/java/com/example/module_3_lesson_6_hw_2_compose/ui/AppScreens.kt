package com.example.module_3_lesson_6_hw_2_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.rooms.RoomsEditViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green10
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green20
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Module_3_Lesson_6_hw_2_ComposeTheme

@Composable
fun MyApp() {
    val navController = rememberNavController()

    MaterialTheme() {
        Box(modifier = Modifier.background(Green20)) {
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
fun LightScreen(
    onLightControlClicked: () -> Unit,
    onLightStatisticsClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.light))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = onLightControlClicked
        ) {
            Text(text = stringResource(id = R.string.light_control))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = onLightStatisticsClicked
        ) {
            Text(text = stringResource(id = R.string.statistics))
        }
    }
}

@Composable
fun LightControlScreen(
    viewModel: RoomsEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val (isCheckedOffice, setCheckedOffice) = remember { mutableStateOf(false) }
    val (isCheckedBedroom, setCheckedBedroom) = remember { mutableStateOf(false) }
    val (isCheckedHall, setCheckedHall) = remember { mutableStateOf(false) }
    val (isCheckedKitchen, setCheckedKitchen) = remember { mutableStateOf(false) }
    val (isCheckedBathroom, setCheckedBathroom) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.light_control))

        SwitchRow(
            text = stringResource(id = R.string.light_office),
            isChecked = isCheckedOffice,
            onChange = {
                setCheckedOffice(it)
                viewModel.updateLightSwitch()
            }
        )
        SwitchRow(
            text = stringResource(id = R.string.light_bedroom),
            isChecked = isCheckedBedroom,
            onChange = setCheckedBedroom
        )
        SwitchRow(
            text = stringResource(id = R.string.light_hall),
            isChecked = isCheckedHall,
            onChange = setCheckedHall
        )
        SwitchRow(
            text = stringResource(id = R.string.light_kitchen),
            isChecked = isCheckedKitchen,
            onChange = setCheckedKitchen
        )
        SwitchRow(
            text = stringResource(id = R.string.light_bathroom),
            isChecked = isCheckedBathroom,
            onChange = setCheckedBathroom
        )

    }
}

@Composable
fun LightStatisticsScreen() {
    var currentMonth by remember { mutableStateOf("") }
    var previousMonth by remember { mutableStateOf("") }
    var currentYear by remember { mutableStateOf("") }
    var isEditingState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isEditingState) {
            Text(text = stringResource(id = R.string.statistics))
            StatisticsRow(
                textTitle = stringResource(id = R.string.statistics_period),
                textValue = stringResource(id = R.string.statistics_kwh)
            )
            StatisticsRow(
                textTitle = stringResource(id = R.string.statistics_current_month),
                textValue = "114"
            )
            StatisticsRow(
                textTitle = stringResource(id = R.string.statistics_previous_month),
                textValue = "203"
            )
            StatisticsRow(
                textTitle = stringResource(id = R.string.statistics_current_year),
                textValue = "2248"
            )
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { isEditingState = true }
            ) {
                Text(text = stringResource(id = R.string.statistics_adjust))
            }
        } else {
            Text(text = stringResource(id = R.string.statistics_adjust))
            StatisticsOutlinedTextFiled(
                value = currentMonth,
                onValueChange = { currentMonth = it },
                labelId = R.string.statistics_current_month
            )
            StatisticsOutlinedTextFiled(
                value = previousMonth,
                onValueChange = { previousMonth = it },
                labelId = R.string.statistics_previous_month
            )
            StatisticsOutlinedTextFiled(
                value = currentYear,
                onValueChange = { currentYear = it },
                labelId = R.string.statistics_current_year
            )
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { isEditingState = false }
            ) {
                Text(text = stringResource(id = R.string.statistics_button_save))
            }
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
        LightStatisticsScreen()
    }
}