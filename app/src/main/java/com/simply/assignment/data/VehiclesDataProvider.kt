package com.simply.assignment.data

import com.simply.assignment.R

object VehiclesDataProvider {
	
	val allVehicles = listOf(
		Vehicle(
			name = "My QX55",
			gasRange = 120,
			imageUrl = R.drawable.qx55
		),
		
		Vehicle(
			name = "Not My QX55",
			gasRange = 360,
			imageUrl = R.drawable.qx55
		)
	)
}