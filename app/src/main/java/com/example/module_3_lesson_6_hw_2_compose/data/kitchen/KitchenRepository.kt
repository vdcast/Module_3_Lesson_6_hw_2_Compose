package com.example.module_3_lesson_6_hw_2_compose.data.kitchen

import kotlinx.coroutines.flow.Flow

interface KitchenRepository {
    suspend fun insert(kitchenItem: KitchenItem)
    suspend fun update(kitchenItem: KitchenItem)
    suspend fun delete(kitchenItem: KitchenItem)
    fun getKitchenByNameStream(name: String): Flow<KitchenItem>
}