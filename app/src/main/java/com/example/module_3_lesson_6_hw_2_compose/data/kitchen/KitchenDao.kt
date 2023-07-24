package com.example.module_3_lesson_6_hw_2_compose.data.kitchen

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KitchenDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kitchenItem: KitchenItem)

    @Update
    suspend fun update(kitchenItem: KitchenItem)

    @Delete
    suspend fun delete(kitchenItem: KitchenItem)

    @Query("SELECT * from kitchen WHERe name = :name")
    fun getKitchenByName(name: String): Flow<KitchenItem>

}