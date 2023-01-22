package com.simply.assignment.data

import kotlinx.coroutines.flow.Flow

interface VehiclesRepository {
	fun getAllVehicles(): Flow<List<Vehicle>>
}