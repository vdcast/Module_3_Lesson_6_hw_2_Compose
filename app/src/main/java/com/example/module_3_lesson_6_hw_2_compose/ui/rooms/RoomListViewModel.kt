package com.example.module_3_lesson_6_hw_2_compose.ui.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.RoomItem
import com.example.module_3_lesson_6_hw_2_compose.data.RoomsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RoomListViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {
    val roomListUiState: StateFlow<RoomListUiState> =
        roomsRepository.getAllRoomsStream().map { RoomListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RoomListUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun updateRoomIsLightOn(roomId: Int, isLightOn: Boolean) {
        val room = roomsRepository.getRoomById(roomId)
        room?.let {
            val updatedRoom = it.copy(isLightOn = isLightOn)
            roomsRepository.updateRoom(updatedRoom)
        }
    }

    suspend fun deleteRoomItem(roomItem: RoomItem) {
        roomsRepository.deleteRoom(roomItem)
    }
}

data class RoomListUiState(
    val roomList: List<RoomItem> = listOf()
)