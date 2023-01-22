package com.simply.assignment.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.simply.assignment.data.Vehicle

@Composable
fun VehicleImage(vehicle: Vehicle) {
	AsyncImage(
		model = ImageRequest.Builder(LocalContext.current)
			.data(vehicle.imageUrl)
			.crossfade(true)
			.build(),
		contentDescription = null,
		modifier = Modifier
			.fillMaxWidth(0.8f)
			.padding(top = 36.dp)
	)
}
