package com.example.module_3_lesson_6_hw_2_compose.model

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}