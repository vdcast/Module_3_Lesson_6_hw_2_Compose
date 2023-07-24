package com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "air_conditioning")
data class AirConditioningItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val currentTemperature: Int,
    val stepTemperature: Int
)
