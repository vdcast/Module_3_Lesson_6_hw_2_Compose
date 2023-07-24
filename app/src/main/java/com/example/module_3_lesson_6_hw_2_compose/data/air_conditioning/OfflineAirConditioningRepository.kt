package com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning

import kotlinx.coroutines.flow.Flow

class OfflineAirConditioningRepository(private val acDao: AirConditioningDao) : AirConditioningRepository {
    override suspend fun insert(airConditioningItem: AirConditioningItem) = acDao.insert(airConditioningItem)

    override suspend fun update(airConditioningItem: AirConditioningItem) = acDao.update(airConditioningItem)

    override suspend fun delete(airConditioningItem: AirConditioningItem) = acDao.delete(airConditioningItem)

    override suspend fun getAcByName(name: String): AirConditioningItem = acDao.getAcByName(name)

    override fun getAllAirConditioning(): Flow<List<AirConditioningItem>> = acDao.getAllAirConditioning()
}