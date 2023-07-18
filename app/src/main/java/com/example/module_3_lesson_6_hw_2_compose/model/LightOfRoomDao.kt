package com.example.module_3_lesson_6_hw_2_compose.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface LightOfRoomDao {
    @Query("SELECT * FROM LightOfRoom")
    fun getAllLightOfRoom(): Flowable<List<LightOfRoom>>

    @Query("SELECT * FROM LightOfRoom WHERE room = :room")
    fun getByRoom(room: String): Single<LightOfRoom>

    @Query("SELECT * FROM LightOfRoom WHERE turnedOn = 1")
    fun getAllTurnedOn(): Flowable<List<LightOfRoom>>

    @Insert
    fun insert(lightOfRoom: LightOfRoom): Completable

    @Update
    fun update(lightOfRoom: LightOfRoom): Completable

    @Delete
    fun delete(lightOfRoom: LightOfRoom): Completable
}