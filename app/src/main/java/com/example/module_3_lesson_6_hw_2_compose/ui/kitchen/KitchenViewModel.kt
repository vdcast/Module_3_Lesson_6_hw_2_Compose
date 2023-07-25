package com.example.module_3_lesson_6_hw_2_compose.ui.kitchen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.kitchen.KitchenItem
import com.example.module_3_lesson_6_hw_2_compose.data.kitchen.KitchenRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class KitchenViewModel(private val kitchenRepository: KitchenRepository) : ViewModel() {

    val kitchenListUiState: StateFlow<KitchenListUiState> =
        kitchenRepository.getAllKitchens().map { KitchenListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KitchenListUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun startCooking() {
        val kitchen = kitchenRepository.getKitchenByName("Kitchen")
        val startedTime = System.currentTimeMillis()
        val finishedTime = startedTime + 10_000

        kitchen.let {
            val updatedCookingTime = it.copy(
                startTime = startedTime,
                finishTime = finishedTime,
                cookingStatus = true
            )
            kitchenRepository.update(updatedCookingTime)
        }
    }

    suspend fun stopCooking() {
        val kitchen = kitchenRepository.getKitchenByName("Kitchen")
        kitchen.let {
            val updatedCookingTime = it.copy(startTime = 0, cookingStatus = false)
            kitchenRepository.update(updatedCookingTime)
        }
    }
    suspend fun stopCookingByButton() {
        val kitchen = kitchenRepository.getKitchenByName("Kitchen")
        kitchen.let {
            val updatedCookingTime = it.copy(startTime = 0, finishTime = System.currentTimeMillis(), cookingStatus = false)
            kitchenRepository.update(updatedCookingTime)
        }
    }

}

data class KitchenListUiState(
    val kitchenList: List<KitchenItem> = listOf()
)