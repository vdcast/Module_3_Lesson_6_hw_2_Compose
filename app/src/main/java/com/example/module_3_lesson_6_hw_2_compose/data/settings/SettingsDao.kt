package com.example.module_3_lesson_6_hw_2_compose.data.settings

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(settingsItem: SettingsItem)

    @Update
    suspend fun update(settingsItem: SettingsItem)

    @Delete
    suspend fun delete(settingsItem: SettingsItem)

    @Query("SELECT * from settings WHERE name = :name")
    suspend fun getSettingByName(name: String): SettingsItem

    @Query("SELECT * from settings")
    fun getAllSettings(): List<SettingsItem>
}