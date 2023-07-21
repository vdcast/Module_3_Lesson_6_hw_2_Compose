package com.example.module_3_lesson_6_hw_2_compose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class RoomItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val isLightOn: Boolean
)
