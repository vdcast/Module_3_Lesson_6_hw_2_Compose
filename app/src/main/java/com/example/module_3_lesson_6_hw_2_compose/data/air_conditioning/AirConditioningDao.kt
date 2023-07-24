package com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AirConditioningDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(airConditioningItem: AirConditioningItem)

    @Update
    suspend fun update(airConditioningItem: AirConditioningItem)

    @Delete
    suspend fun delete(airConditioningItem: AirConditioningItem)

    @Query("SELECT * from air_conditioning where name = :name")
    suspend fun getAcByName(name: String): AirConditioningItem
}