package com.example.module_3_lesson_6_hw_2_compose.data.settings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

)
