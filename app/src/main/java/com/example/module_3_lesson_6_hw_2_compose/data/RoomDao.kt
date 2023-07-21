package com.example.module_3_lesson_6_hw_2_compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(roomItem: RoomItem)

    @Update
    suspend fun update(roomItem: RoomItem)

    @Delete
    suspend fun delete(roomItem: RoomItem)

    @Query("SELECT * from rooms ORDER BY name ASC")
    fun getAllRooms(): Flow<List<RoomItem>>

    @Query("SELECT * from rooms WHERE isLightOn = :isLightOn")
    fun getAllByTurned(isLightOn: Boolean): Flow<List<RoomItem>>

    @Query("SELECT * from rooms WHERE id =:id")
    fun getRoomById(id: Int): Flow<RoomItem>

    @Query("SELECT * from rooms WHERE name = :name")
    fun getRoomByName(name: String): Flow<RoomItem>

}