package com.example.module_3_lesson_6_hw_2_compose.ui.rooms

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.RoomsRepository
import com.example.module_3_lesson_6_hw_2_compose.data.network.API
import com.example.module_3_lesson_6_hw_2_compose.data.network.RetrofitClient
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.module_3_lesson_6_hw_2_compose.data.RoomItem

class RoomsEditViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {

    var roomItemUiState by mutableStateOf(RoomItemUiState())
        private set


    private val retrofit = RetrofitClient.getClient("https://catfact.ninja/")
        .create(API::class.java)

    fun updateLightSwitch() {
        viewModelScope.launch {
            val response = retrofit.fetchSomeData()
            if (response.isSuccessful) {
                Log.d("MYLOG", response.body()?.fact.toString())

                roomsRepository.updateRoom(roomItemUiState.roomItemDetails.toRoomItem())

            } else {
                Log.d("MYLOG", "Response not successful. Code: ${response.code()}, Message: ${response.message()}")
            }
        }
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