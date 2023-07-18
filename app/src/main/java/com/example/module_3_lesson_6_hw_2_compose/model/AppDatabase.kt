package com.example.module_3_lesson_6_hw_2_compose.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LightOfRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLightOfRoomDao(): LightOfRoomDao
}