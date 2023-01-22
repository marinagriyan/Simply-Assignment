package com.simply.assignment.data

import com.simply.assignment.ui.DoorsState
import com.simply.assignment.ui.EngineState

data class Vehicle(
	val name: String,
	val gasRange: Int,
	val imageUrl: Any,
	val doorsState: DoorsState = DoorsState.LOCKED,
	val engineState: EngineState = EngineState.STOP
)