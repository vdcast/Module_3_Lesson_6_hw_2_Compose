package com.example.module_3_lesson_6_hw_2_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning.AirConditioningItem
import com.example.module_3_lesson_6_hw_2_compose.data.air_conditioning.AirConditioningRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AirConditioningViewModel(private val airConditioningRepository: AirConditioningRepository) :
    ViewModel() {

    val acListUiState: StateFlow<AirConditioningListUiState> =
        airConditioningRepository.getAllAirConditioning().map { AirConditioningListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AirConditioningListUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun temperatureUp(name: String) {
        val acItem = airConditioningRepository.getAcByName(name)
        val updatedTemperature = acItem.currentTemperature + acItem.stepTemperature
        val updatedAcItem = acItem.copy(currentTemperature = updatedTemperature)
        airConditioningRepository.update(updatedAcItem)
    }

    suspend fun temperatureDown(name: String) {
        val acItem = airConditioningRepository.getAcByName(name)
        val updatedTemperature = acItem.currentTemperature - acItem.stepTemperature
        val updatedAcItem = acItem.copy(currentTemperature = updatedTemperature)
        airConditioningRepository.update(updatedAcItem)
    }

}

data class AirConditioningListUiState(
    val acList: List<AirConditioningItem> = listOf()
)

data class AirConditioningItemDetails(
    val id: Int = 0,
    val name: String = "",
    val currentTemperature: Int = 0,
    val stepTemperature: Int = 1
)

data class AirConditioningItemUiState(
    val airConditioningItemDetails: AirConditioningItemDetails = AirConditioningItemDetails()
)

fun AirConditioningItemDetails.toAirConditioningItem(): AirConditioningItem = AirConditioningItem(
    id = id,
    name = name,
    currentTemperature = currentTemperature,
    stepTemperature = stepTemperature
)

fun AirConditioningItem.toAirConditioningItemDetails(): AirConditioningItemDetails =
    AirConditioningItemDetails(
        id = id,
        name = name,
        currentTemperature = currentTemperature,
        stepTemperature = stepTemperature
    )

fun AirConditioningItem.toAirConditioningItemUiState(): AirConditioningItemUiState =
    AirConditioningItemUiState(
        airConditioningItemDetails = this.toAirConditioningItemDetails()
    )
