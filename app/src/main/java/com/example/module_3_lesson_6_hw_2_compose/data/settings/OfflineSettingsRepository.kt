package com.example.module_3_lesson_6_hw_2_compose.data.settings

class OfflineSettingsRepository(private val settingsDao: SettingsDao) : SettingsRepository {
    override suspend fun insert(settingsItem: SettingsItem) = settingsDao.insert(settingsItem)

    override suspend fun update(settingsItem: SettingsItem) = settingsDao.update(settingsItem)

    override suspend fun delete(settingsItem: SettingsItem) = settingsDao.delete(settingsItem)

    override suspend fun getSettingByName(name: String): SettingsItem = settingsDao.getSettingByName(name)

    override fun getAllSettings(): List<SettingsItem> = settingsDao.getAllSettings()

}