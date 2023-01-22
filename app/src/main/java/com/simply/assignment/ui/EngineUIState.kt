package com.simply.assignment.ui

data class EngineUIState(
	val state: EngineState = EngineState.STOP,
	val startStateLoading: Boolean = false,
	val stopStateLoading: Boolean = false
)

enum class EngineState {
	START, STOP
}