package com.example.module_3_lesson_6_hw_2_compose.ui.light.light_control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.module_3_lesson_6_hw_2_compose.R
import com.example.module_3_lesson_6_hw_2_compose.ui.AddRoom
import com.example.module_3_lesson_6_hw_2_compose.ui.AppViewModelProvider
import com.example.module_3_lesson_6_hw_2_compose.ui.RowEditing
import com.example.module_3_lesson_6_hw_2_compose.ui.SwitchRow
import kotlinx.coroutines.launch

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
                ) { Text(text = stringResource(id = R.string.add_room)) }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    onClick = {
                        isEditing = true
                    }
                ) { Text(text = stringResource(id = R.string.edit_rooms)) }
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
                ) { Text(text = stringResource(id = R.string.save)) }
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