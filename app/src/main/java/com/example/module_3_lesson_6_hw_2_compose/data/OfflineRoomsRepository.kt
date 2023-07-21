package com.example.module_3_lesson_6_hw_2_compose.data

import kotlinx.coroutines.flow.Flow

class OfflineRoomsRepository(private val roomDao: RoomDao) : RoomsRepository {
    override fun getAllRoomsStream(): Flow<List<RoomItem>> = roomDao.getAllRooms()

    override fun getAllByTurnedStream(isLightOn: Boolean): Flow<List<RoomItem>> =
        roomDao.getAllByTurned(isLightOn)

    override fun getRoomByIdStream(id: Int): Flow<RoomItem?> = roomDao.getRoomById(id)

    override fun getRoomByNameStream(name: String): Flow<RoomItem?> = roomDao.getRoomByName(name)

    override suspend fun insertRoom(roomItem: RoomItem) = roomDao.insert(roomItem)

    override suspend fun updateRoom(roomItem: RoomItem) = roomDao.update(roomItem)

    override suspend fun deleteRoom(roomItem: RoomItem) = roomDao.delete(roomItem)
}