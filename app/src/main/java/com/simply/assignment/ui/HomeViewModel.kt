package com.simply.assignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.assignment.data.Vehicle
import com.simply.assignment.data.VehiclesRepository
import com.simply.assignment.data.VehiclesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val vehiclesRepository : VehiclesRepository = VehiclesRepositoryImpl()): ViewModel() {
	private val _uiState = MutableStateFlow(HomeUIState())
	val uiState: StateFlow<HomeUIState> = _uiState
	
	init {
		getVehicles()
	}
	
	private fun getVehicles() {
		viewModelScope.launch {
			vehiclesRepository.getAllVehicles()
				.catch { ex ->
					_uiState.value = HomeUIState(error = ex.message)
				}
				.collect { vehicles ->
					_uiState.value = HomeUIState(vehicles)
				}
		}
	}
	
	fun unlockVehicle(id: Int){
		changeDoorsState(id, DoorsState.UNLOCKED)
	}
	
	fun lockVehicle(id: Int){
		changeDoorsState(id, DoorsState.LOCKED)
	}
	
	private fun changeDoorsState(id: Int, state: DoorsState){
		viewModelScope.launch {
			withContext(Dispatchers.IO){
				delay(5000)
				val vehicles = uiState.value.vehicles.map { it.copy() }
				vehicles.find { it.id == id }?.let {
					it.doorsState = state
				}
				_uiState.value = _uiState.value.copy(
					vehicles = vehicles
				)
			}
		}
	
	}
}

data class HomeUIState(
	val vehicles: List<Vehicle> = emptyList(),
	val error: String? = null
)
