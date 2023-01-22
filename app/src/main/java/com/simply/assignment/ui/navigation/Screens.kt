package com.simply.assignment.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.simply.assignment.R

enum class ScreenTabs(
	@StringRes val title: Int,
	@DrawableRes val icon: Int,
	val route: String
) {
	HOME(R.string.home, R.drawable.ic_home, Destinations.HOME),
	VEHICLE(R.string.vehicle, R.drawable.ic_vehicle, Destinations.VEHICLE),
	LOCATION(R.string.location, R.drawable.ic_location, Destinations.LOCATION),
	SETTINGS(R.string.settings, R.drawable.ic_settings, Destinations.SETTINGS),
}

private object Destinations {
	const val HOME = "main/home"
	const val VEHICLE = "main/vehicle"
	const val LOCATION = "main/location"
	const val SETTINGS = "main/settings"
}