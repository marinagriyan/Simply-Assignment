package com.simply.assignment.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colors = lightColors(
	primary = brownSugar,
	onPrimary = Color.White,
	secondary = charcoalGray,
	onSecondary = grey,
	background = light,
	onBackground = lightestGrey,
)

@Composable
fun Theme(
	content: @Composable () -> Unit
) {
	MaterialTheme(
		colors = colors,
		content = content
	)
}