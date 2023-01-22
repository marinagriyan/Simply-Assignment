package com.simply.assignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.assignment.data.Vehicle
import com.simply.assignment.data.VehiclesRepository
import com.simply.assignment.data.VehiclesRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val vehiclesRepository : VehiclesRepository = VehiclesRepositoryImpl()): ViewModel() {
	private val _vehicles: MutableStateFlow<List<Vehicle>> = MutableStateFlow(emptyList())
	val vehicles: StateFlow<List<Vehicle>> get() = _vehicles.asStateFlow()
	
	init {
		getVehicles()
	}
	
	private fun getVehicles() {
		viewModelScope.launch {
			vehiclesRepository.getAllVehicles()
				.catch { ex ->
					Timber.e(ex)
				}
				.collect { vehicles ->
					_vehicles.value = vehicles
				}
		}
	}
	
}
