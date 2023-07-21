package com.example.module_3_lesson_6_hw_2_compose.ui.rooms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.RoomItem
import com.example.module_3_lesson_6_hw_2_compose.data.RoomsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.network.API
import com.example.module_3_lesson_6_hw_2_compose.data.network.RetrofitClient
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

    private val retrofit = RetrofitClient.getClient("https://catfact.ninja/")
        .create(API::class.java)

    suspend fun updateRoomIsLightOn(roomId: Int, isLightOn: Boolean) {
        val response = retrofit.fetchSomeData()
        if (response.isSuccessful) {
            val room = roomsRepository.getRoomById(roomId)
            room?.let {
                val updatedRoom = it.copy(isLightOn = isLightOn)
                roomsRepository.updateRoom(updatedRoom)
            }
            Log.d("MYLOG", response.body()?.fact.toString())
        } else {
            Log.d("MYLOG", "Response not successful. Code: ${response.code()}, Message: ${response.message()}")
        }
    }

    suspend fun deleteRoomItem(roomItem: RoomItem) {
        val response = retrofit.fetchSomeData()
        if (response.isSuccessful) {
            roomsRepository.deleteRoom(roomItem)
            Log.d("MYLOG", response.body()?.fact.toString())
        } else {
            Log.d("MYLOG", "Response not successful. Code: ${response.code()}, Message: ${response.message()}")
        }
    }
}

data class RoomListUiState(
    val roomList: List<RoomItem> = listOf()
)