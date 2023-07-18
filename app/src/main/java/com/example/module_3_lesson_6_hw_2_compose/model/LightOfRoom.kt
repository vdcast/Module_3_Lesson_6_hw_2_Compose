package com.example.module_3_lesson_6_hw_2_compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LightOfRoom(
    @PrimaryKey
    val room: String,
    val turnedOn: Boolean
)
