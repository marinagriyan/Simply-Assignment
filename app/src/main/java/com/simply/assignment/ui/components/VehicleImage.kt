package com.simply.assignment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.simply.assignment.data.Vehicle

@Composable
fun VehicleImage(vehicle: Vehicle) {
	val painter = rememberAsyncImagePainter(
		model = ImageRequest.Builder(LocalContext.current)
			.data(vehicle.imageUrl)
			.crossfade(true)
			.build()
	)
	Image(
		painter = painter,
		contentDescription = null,
		contentScale = ContentScale.Inside,
		modifier = Modifier.fillMaxWidth(0.8f).padding(top = 36.dp),
	)
}
