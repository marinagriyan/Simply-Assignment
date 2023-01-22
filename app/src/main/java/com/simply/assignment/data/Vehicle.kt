package com.simply.assignment.data

import com.simply.assignment.ui.DoorsState
import com.simply.assignment.ui.EngineState

data class Vehicle(
	val id: Int,
	val name: String,
	val gasRange: Int,
	val imageUrl: Any,
	var doorsState: DoorsState = DoorsState.LOCKED,
	var engineState: EngineState = EngineState.STOP
)