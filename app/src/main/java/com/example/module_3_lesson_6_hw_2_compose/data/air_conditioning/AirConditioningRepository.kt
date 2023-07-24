package com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning

import kotlinx.coroutines.flow.Flow


interface AirConditioningRepository {
    suspend fun insert(airConditioningItem: AirConditioningItem)

    suspend fun update(airConditioningItem: AirConditioningItem)

    suspend fun delete(airConditioningItem: AirConditioningItem)

    suspend fun getAcByName(name: String): AirConditioningItem

    fun getAllAirConditioning(): Flow<List<AirConditioningItem>>
}