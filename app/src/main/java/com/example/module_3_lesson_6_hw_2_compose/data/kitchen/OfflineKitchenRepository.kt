package com.example.module_3_lesson_6_hw_2_compose.data.kitchen

import kotlinx.coroutines.flow.Flow

class OfflineKitchenRepository(private val kitchenDao: KitchenDao) : KitchenRepository {
    override suspend fun insert(kitchenItem: KitchenItem) = kitchenDao.insert(kitchenItem)

    override suspend fun update(kitchenItem: KitchenItem) = kitchenDao.update(kitchenItem)

    override suspend fun delete(kitchenItem: KitchenItem) = kitchenDao.delete(kitchenItem)

    override fun getKitchenByNameStream(name: String): Flow<KitchenItem> =
        kitchenDao.getKitchenByName(name)
}