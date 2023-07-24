package com.example.module_3_lesson_6_hw_2_compose.data.kitchen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kitchen")
data class KitchenItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val time: Long,
    val cookingStatus: Boolean
)
