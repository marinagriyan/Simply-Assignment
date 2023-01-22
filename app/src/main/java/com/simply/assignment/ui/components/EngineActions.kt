package com.simply.assignment.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.assignment.R
import com.simply.assignment.data.Vehicle

@Composable
fun Engine(
	vehicle: Vehicle,
	modifier: Modifier = Modifier,
	onStart: () -> Unit,
	onStop: () -> Unit
){
	Column(modifier = modifier) {
		Text(
			text = LocalContext.current.getString(R.string.engine),
			color = MaterialTheme.colors.secondary,
			fontWeight = FontWeight.ExtraBold,
			fontSize = 18.sp
		)
		Card{
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
				horizontalArrangement = Arrangement.Center
			){
				EngineButton(
					title = R.string.start,
					onClick = onStart
				)
				Spacer(modifier = Modifier.width(8.dp))
				EngineButton(
					title = R.string.stop,
					onClick = onStop
				)
			}
		}
	}
}

@Composable
private fun EngineButton(
	@StringRes title: Int,
	onClick: () -> Unit
){
	Box(
		modifier = Modifier
			.size(56.dp)
			.clip(CircleShape)
			.background(Color.Black)
			.clickable { onClick() }
	){
		Text(
			text = LocalContext.current.getString(title).uppercase(),
			color = Color.White,
			fontSize = 12.sp,
			modifier = Modifier.align(Alignment.Center)
		)
	}
	
}