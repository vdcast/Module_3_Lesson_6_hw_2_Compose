package com.example.module_3_lesson_6_hw_2_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.RoomListViewModel
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.RoomsAddViewModel
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green10
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green20
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Green30
import com.example.module_3_lesson_6_hw_2_compose.ui.theme.Module_3_Lesson_6_hw_2_ComposeTheme
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.AirConditioningViewModel
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.AppViewModelProvider
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.KitchenViewModel
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.StatisticsEditViewModel
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.StatisticsListViewModel
import com.example.module_3_lesson_6_hw_2_compose.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

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
    viewModelRoomsAdd: RoomsAddViewModel = viewModel(factory = AppViewModelProvider.Factory),
    viewModelRoomList: RoomListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var isAdding by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    val roomListUiState by viewModelRoomList.roomListUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isAdding) {
            if (!isEditing) {
                Text(text = stringResource(id = R.string.light_control))
                if (roomListUiState.roomList.isEmpty()) {
                    Text(text = stringResource(id = R.string.rooms_empty))
                } else {
                    LazyColumn() {
                        itemsIndexed(roomListUiState.roomList) { index, item ->
                            SwitchRow(
                                text = item.name,
                                isChecked = item.isLightOn,
                                onChange = {
                                    coroutineScope.launch {
                                        viewModelRoomList.updateRoomIsLightOn(item.id, it)
                                    }
                                }
                            )
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    onClick = {
                        isAdding = true
                    }
                ) {
                    Text(text = stringResource(id = R.string.add_room))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    onClick = {
                        isEditing = true
                    }
                ) {
                    Text(text = stringResource(id = R.string.edit_rooms))
                }
            } else {
                Text(text = stringResource(id = R.string.light_delete_room))
                LazyColumn() {
                    itemsIndexed(roomListUiState.roomList) { index, item ->
                        RowEditing(
                            text = item.name,
                            onDeleteClicked = {
                                coroutineScope.launch {
                                    viewModelRoomList.deleteRoomItem(item)
                                }
                            }
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    onClick = {
                        isEditing = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.save))
                }
            }
        } else {
            AddRoom(
                roomItemUiState = viewModelRoomsAdd.roomItemUiState,
                onRoomItemValueChange = viewModelRoomsAdd::updateUiState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModelRoomsAdd.saveRoom()
                        isAdding = false
                    }
                },
                onCancelClick = { isAdding = false }
            )
        }
    }
}

@Composable
fun LightStatisticsScreen(
    viewModelStatisticsList: StatisticsListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    viewModelStatisticsEdit: StatisticsEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val statisticsListUiState by viewModelStatisticsList.statisticsListUiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    var currentMonth by remember { mutableStateOf("") }
    var previousMonth by remember { mutableStateOf("") }
    var currentYear by remember { mutableStateOf("") }
    var isEditingState by remember { mutableStateOf(false) }

    LaunchedEffect(viewModelStatisticsEdit) {
        currentMonth = viewModelStatisticsEdit.getCurrentMonthValue()
        previousMonth = viewModelStatisticsEdit.getPreviousMonthValue()
        currentYear = viewModelStatisticsEdit.getCurrentYearValue()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isEditingState) {
            Text(text = stringResource(id = R.string.statistics))
            LazyColumn() {
                itemsIndexed(statisticsListUiState.statisticsList) { index, item ->
                    StatisticsRow(textTitle = item.name, textValue = item.value.toString())
                }
            }

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
                onValueChange = {
                    currentMonth = it
                    viewModelStatisticsEdit.updateUiState(
                        viewModelStatisticsEdit.statisticsItemUiState.statisticsItemDetails
                            .copy(value = if (it.isEmpty()) 0.0 else currentMonth.toDouble())
                    )
                    coroutineScope.launch {
                        viewModelStatisticsEdit.saveTest(
                            "Current month", if (it.isEmpty()) 0.0 else currentMonth.toDouble()
                        )
                    }
                },
                labelId = R.string.statistics_current_month
            )
            StatisticsOutlinedTextFiled(
                value = previousMonth,
                onValueChange = {
                    previousMonth = it
                    viewModelStatisticsEdit.updateUiState(
                        viewModelStatisticsEdit.statisticsItemUiState.statisticsItemDetails
                            .copy(value = if (it.isEmpty()) 0.0 else previousMonth.toDouble())
                    )
                    coroutineScope.launch {
                        viewModelStatisticsEdit.saveTest(
                            "Previous month", if (it.isEmpty()) 0.0 else previousMonth.toDouble()
                        )
                    }
                },
                labelId = R.string.statistics_previous_month
            )
            StatisticsOutlinedTextFiled(
                value = currentYear,
                onValueChange = {
                    currentYear = it
                    viewModelStatisticsEdit.updateUiState(
                        viewModelStatisticsEdit.statisticsItemUiState.statisticsItemDetails
                            .copy(value = if (it.isEmpty()) 0.0 else currentYear.toDouble())
                    )
                    coroutineScope.launch {
                        viewModelStatisticsEdit.saveTest(
                            "Current year", if (it.isEmpty()) 0.0 else currentYear.toDouble()
                        )
                    }
                },
                labelId = R.string.statistics_current_year
            )
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { isEditingState = false }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }


    }
}

@Composable
fun KitchenScreen(
    viewModelKitchen: KitchenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val kitchenUiState by viewModelKitchen.kitchenListUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.kitchen))
        Text(text = stringResource(id = R.string.cooking_status))

        kitchenUiState.kitchenList.firstOrNull()?.let { item ->
            val currentTime = System.currentTimeMillis()
            val startedTime = SimpleDateFormat("HH:mm:ss").format(item.startTime)
            val finishedTime = SimpleDateFormat("HH:mm:ss").format(item.finishTime)
            Text(
                text = if (item.cookingStatus) stringResource(id = R.string.cooking_status_started)
                else stringResource(id = R.string.cooking_status_finished, finishedTime)
            )
            if (item.cookingStatus) {
                Text(text = stringResource(id = R.string.cooking_started, startedTime))
                Text(text = stringResource(id = R.string.cooking_finished, finishedTime))
                if (currentTime > item.finishTime) {
                    coroutineScope.launch {
                        viewModelKitchen.stopCooking()
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelKitchen.startCooking()
                }
            }
        ) { Text(text = stringResource(id = R.string.start_cooking)) }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelKitchen.stopCookingByButton()
                }
            }
        ) { Text(text = stringResource(id = R.string.stop_cooking)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    viewModelTask: TaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Box(modifier = Modifier.fillMaxSize()) {
        var isAdding by remember{ mutableStateOf(false) }
        val taskListUiState by viewModelTask.taskListUiState.collectAsState()
        var deleteAlertDialog by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()

        if (!isAdding) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.padding_m_l),
                                vertical = dimensionResource(id = R.dimen.padding_small)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                        Text(
                            modifier = Modifier
                                .padding(all = dimensionResource(id = R.dimen.padding_small)),
                            text = stringResource(id = R.string.tasks),
                            fontSize = 20.sp
                        )
                        if (taskListUiState.taskList.isNotEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .scale(1.25f)
                                    .clickable { deleteAlertDialog = true },
                                imageVector = Icons.Default.Delete,
                                contentDescription = "clear all tasks"
                            )
                        } else {
                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                        }
                    }
                }
                if (taskListUiState.taskList.isEmpty()) {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(all = dimensionResource(id = R.dimen.padding_medium)),
                            text = stringResource(id = R.string.task_list_empty),
                            fontSize = 16.sp
                        )
                    }
                } else {
                    itemsIndexed(taskListUiState.taskList) { index, item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    min = dimensionResource(id = R.dimen.padding_xxlarge),
                                    max = dimensionResource(id = R.dimen.padding_xxxlarge)
                                )
                                .padding(
                                    vertical = dimensionResource(id = R.dimen.padding_xsmall),
                                    horizontal = dimensionResource(id = R.dimen.padding_small)
                                )
                                .verticalScroll(rememberScrollState()),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_medium)),
                            elevation = CardDefaults.cardElevation(
                                dimensionResource(id = R.dimen.padding_xsmall)
                            ),
                            colors = CardDefaults.cardColors(Green30),
                            onClick = { }
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    vertical = dimensionResource(id = R.dimen.padding_medium),
                                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                                )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(
                                            start = dimensionResource(id = R.dimen.padding_xsmall),
                                            bottom = dimensionResource(id = R.dimen.padding_small)
                                        ),
                                    text = item.taskTitle,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                Text(
                                    modifier = Modifier,
                                    text = item.taskText,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                containerColor = Green10,
                contentColor = Color.White,
                shape = CircleShape,
                onClick = { isAdding = true }
            ) { Icon(imageVector = Icons.Default.Add, contentDescription = "add task") }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var taskTitle by remember { mutableStateOf("") }
                var taskText by remember { mutableStateOf("") }
                var isTitleEmpty by remember { mutableStateOf(false) }
                var isTextEmpty by remember { mutableStateOf(false) }

                val coroutineScope = rememberCoroutineScope()

                val focusManager = LocalFocusManager.current

                Text(text = stringResource(id = R.string.add_task))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = taskTitle,
                    onValueChange = {
                        taskTitle = it

                        viewModelTask.updateTaskUiState(
                            viewModelTask.taskAddItemUiState.taskItemDetails.copy(taskTitle = taskTitle)
                        )

                        isTitleEmpty = it.isEmpty()
                    },
                    isError = isTitleEmpty,
                    label = { Text(stringResource(id = R.string.title)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = taskText,
                    onValueChange = {
                        taskText = it
                        viewModelTask.updateTaskUiState(
                            viewModelTask.taskAddItemUiState.taskItemDetails.copy(taskText = taskText)
                        )
                        isTextEmpty = it.isEmpty()
                    },
                    isError = isTextEmpty,
                    label = { Text(stringResource(id = R.string.text)) },
                    maxLines = 10,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    enabled = (taskTitle.isNotEmpty() && taskText.isNotEmpty()),
                    onClick = {
                        coroutineScope.launch {
                            viewModelTask.saveTask()
                            isAdding = false
                        }
                    }
                ) { Text(text = stringResource(id = R.string.save)) }
                Button(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    onClick = {
                        isAdding = false
                    }
                ) { Text(text = stringResource(id = R.string.cancel)) }
            }
        }

        if (deleteAlertDialog) {
            AlertDialog(
                onDismissRequest = {  },
                title = { Text(text = stringResource(id = R.string.delete_all_title)) },
                text = { Text( stringResource(id = R.string.delete_all_body) ) },
                confirmButton = {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModelTask.deleteAll()
                                deleteAlertDialog = false
                            }
                        }
                    ) { Text(stringResource(id = R.string.delete_all_button_yes)) }
                },
                dismissButton = {
                    Button(
                        onClick = { deleteAlertDialog = false }
                    ) { Text(stringResource(id = R.string.delete_all_button_no)) }
                }
            )
        }
    }
}

@Composable
fun AirConditioningScreen(
    viewModelAc: AirConditioningViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val acListUiState by viewModelAc.acListUiState.collectAsState()
    var currentTemperature by remember { mutableStateOf(0) }
    var stepTemperature by remember { mutableStateOf(0) }
    var acName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    acListUiState.acList.firstOrNull()?.let { item ->
        currentTemperature = item.currentTemperature
        stepTemperature = item.stepTemperature
        acName = item.name
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.air_conditioning))
        Text(text = stringResource(id = R.string.current_temperature, currentTemperature))
        Text(text = stringResource(id = R.string.step_temperature, stepTemperature))
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelAc.temperatureUp(acName)
                }
            }
        ) {
            Text(text = stringResource(id = R.string.temperature_up))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                coroutineScope.launch {
                    viewModelAc.temperatureDown(acName)
                }
            }
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