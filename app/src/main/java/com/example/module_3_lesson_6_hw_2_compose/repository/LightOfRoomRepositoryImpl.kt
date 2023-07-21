package com.example.module_3_lesson_6_hw_2_compose.repository

import com.example.module_3_lesson_6_hw_2_compose.model.LightOfRoom
import com.example.module_3_lesson_6_hw_2_compose.model.LightOfRoomDao

class LightOfRoomRepositoryImpl(private val lightOfRoomDao: LightOfRoomDao) : LightOfRoomRepository {
    override fun getAllLightOfRoom() = lightOfRoomDao.getAllLightOfRoom()
    override fun getByRoom(room: String) = lightOfRoomDao.getByRoom(room)
    override fun getAllTurnedOn() = lightOfRoomDao.getAllTurnedOn()
    override fun insert(lightOfRoom: LightOfRoom) = lightOfRoomDao.insert(lightOfRoom)
    override fun update(lightOfRoom: LightOfRoom) = lightOfRoomDao.update(lightOfRoom)
    override fun delete(lightOfRoom: LightOfRoom) = lightOfRoomDao.delete(lightOfRoom)
}