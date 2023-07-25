package com.example.module_3_lesson_6_hw_2_compose.ui.light_rooms_and_statistics

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.network.API
import com.example.module_3_lesson_6_hw_2_compose.data.network.RetrofitClient
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.module_3_lesson_6_hw_2_compose.data.rooms.RoomItem

class RoomsAddViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {

    var roomItemUiState by mutableStateOf(RoomItemUiState())
        private set


    private val retrofit = RetrofitClient.getClient("https://catfact.ninja/")
        .create(API::class.java)


    fun updateUiState(roomItemDetails: RoomItemDetails) {
        roomItemUiState = RoomItemUiState(roomItemDetails = roomItemDetails)
    }

    suspend fun saveRoom() {
        val response = retrofit.fetchSomeData()
        if (response.isSuccessful) {
            roomsRepository.insertRoom(roomItemUiState.roomItemDetails.toRoomItem())
            Log.d("MYLOG", response.body()?.fact.toString())
        } else {
            Log.d("MYLOG", "Response not successful. Code: ${response.code()}, Message: ${response.message()}")
        }
    }


}

data class RoomItemDetails(
    val id: Int = 0,
    val name: String = "",
    val isLightOn: Boolean = false
)

data class RoomItemUiState(
    val roomItemDetails: RoomItemDetails = RoomItemDetails()
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