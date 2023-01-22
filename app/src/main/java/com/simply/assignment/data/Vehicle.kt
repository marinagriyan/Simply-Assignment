package com.simply.assignment.data

import androidx.annotation.StringRes
import com.simply.assignment.R

data class Vehicle(
	val id: Int,
	val name: String,
	val gasRange: Int,
	val imageUrl: Any,
	var doorsState: DoorsState = DoorsState.LOCKED,
	var engineState: EngineState = EngineState.STOP
)

enum class EngineState{
	START,
	STOP
}

enum class DoorsState(
	@StringRes val currentState: Int
) {
	LOCKED(R.string.locked),
	UNLOCKED(R.string.unlocked)
}