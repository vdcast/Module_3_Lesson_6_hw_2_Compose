package com.example.module_3_lesson_6_hw_2_compose.data.statistics

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticsDao{

    @Query("SELECT * from statistics")
    fun getAllStatistics(): Flow<List<StatisticsItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(statisticsItem: StatisticsItem)

    @Update
    suspend fun update(statisticsItem: StatisticsItem)

    @Delete
    suspend fun delete(statisticsItem: StatisticsItem)
}
