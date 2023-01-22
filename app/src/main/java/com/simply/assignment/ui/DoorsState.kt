package com.simply.assignment.ui

import androidx.annotation.StringRes
import com.simply.assignment.R

enum class DoorsState(
	@StringRes val currentState: Int
) {
	LOCKED(R.string.locked),
	UNLOCKED(R.string.unlocked)
}