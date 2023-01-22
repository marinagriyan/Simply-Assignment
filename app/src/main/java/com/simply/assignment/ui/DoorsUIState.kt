package com.simply.assignment.ui

data class DoorsUIState(
	val state: DoorsState = DoorsState.LOCKED,
	val lockStateLoading: Boolean = false,
	val unlockStateLoading: Boolean = false
)

enum class DoorsState {
	LOCKED, UNLOCKED
}