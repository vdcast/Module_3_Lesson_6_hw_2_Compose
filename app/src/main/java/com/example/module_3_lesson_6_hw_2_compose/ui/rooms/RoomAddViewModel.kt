package com.example.module_3_lesson_6_hw_2_compose.ui.rooms

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.module_3_lesson_6_hw_2_compose.data.RoomsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.network.API
import com.example.module_3_lesson_6_hw_2_compose.data.network.RetrofitClient
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.module_3_lesson_6_hw_2_compose.data.RoomItem

class RoomsAddViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {

    var roomItemUiState by mutableStateOf(RoomItemUiState())
        private set


    private val retrofit = RetrofitClient.getClient("https://catfact.ninja/")
        .create(API::class.java)


    fun updateUiState(roomItemDetails: RoomItemDetails) {
        roomItemUiState =
            RoomItemUiState(roomItemDetails = roomItemDetails)
    }

    suspend fun saveRoom() {
        roomsRepository.insertRoom(roomItemUiState.roomItemDetails.toRoomItem())
    }
}

data class RoomItemUiState(
    val roomItemDetails: RoomItemDetails = RoomItemDetails()
)

data class RoomItemDetails(
    val id: Int = 0,
    val name: String = "",
    val isLightOn: Boolean = false
)

fun RoomItemDetails.toRoomItem(): RoomItem = RoomItem(
    id = id,
    name = name,
    isLightOn = isLightOn
)

fun RoomItem.toRoomItemDetails(): RoomItemDetails = RoomItemDetails(
    id = id,
    name = name,
    isLightOn = isLightOn
)

fun RoomItem.toRoomItemUiState(): RoomItemUiState = RoomItemUiState(
    roomItemDetails = this.toRoomItemDetails()
)