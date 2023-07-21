package com.example.module_3_lesson_6_hw_2_compose.repository

import com.example.module_3_lesson_6_hw_2_compose.model.LightOfRoom
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LightOfRoomRepository {
    fun getAllLightOfRoom(): Flowable<List<LightOfRoom>>
    fun getByRoom(room: String): Single<LightOfRoom>
    fun getAllTurnedOn(): Flowable<List<LightOfRoom>>
    fun insert(lightOfRoom: LightOfRoom): Completable
    fun update(lightOfRoom: LightOfRoom): Completable
    fun delete(lightOfRoom: LightOfRoom): Completable
}