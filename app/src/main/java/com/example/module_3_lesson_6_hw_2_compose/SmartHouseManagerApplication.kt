package com.example.module_3_lesson_6_hw_2_compose

import android.app.Application
import com.example.module_3_lesson_6_hw_2_compose.data.AppContainer
import com.example.module_3_lesson_6_hw_2_compose.data.AppDataContainer

class SmartHouseManagerApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}