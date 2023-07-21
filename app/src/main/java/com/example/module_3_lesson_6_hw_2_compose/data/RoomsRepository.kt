package com.example.module_3_lesson_6_hw_2_compose.data

import kotlinx.coroutines.flow.Flow

interface RoomsRepository {
    fun getAllRoomsStream(): Flow<List<RoomItem>>
    fun getAllByTurnedStream(isLightOn: Boolean): Flow<List<RoomItem>>
    fun getRoomByIdStream(id: Int): Flow<RoomItem?>
    fun getRoomByNameStream(name: String): Flow<RoomItem?>
    suspend fun insertRoom(roomItem: RoomItem)
    suspend fun updateRoom(roomItem: RoomItem)
    suspend fun deleteRoom(roomItem: RoomItem)
}