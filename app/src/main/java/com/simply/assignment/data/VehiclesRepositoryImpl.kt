package com.simply.assignment.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VehiclesRepositoryImpl: VehiclesRepository {
	
	override fun getAllVehicles(): Flow<List<Vehicle>> = flow {
		emit(VehiclesDataProvider.allVehicles)
	}
}