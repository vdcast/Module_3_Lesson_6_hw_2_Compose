package com.example.module_3_lesson_6_hw_2_compose.data.settings

interface SettingsRepository {
    suspend fun insert(settingsItem: SettingsItem)

    suspend fun update(settingsItem: SettingsItem)

    suspend fun delete(settingsItem: SettingsItem)

    suspend fun getSettingByName(name: String): SettingsItem

    fun getAllSettings(): List<SettingsItem>
}