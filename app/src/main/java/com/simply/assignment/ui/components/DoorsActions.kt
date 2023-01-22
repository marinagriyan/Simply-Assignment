package com.simply.assignment.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.assignment.R
import com.simply.assignment.data.Vehicle
import com.simply.assignment.ui.DoorsState

@Composable
fun Doors(
	vehicle: Vehicle,
	modifier: Modifier = Modifier,
	onLock: () -> Unit,
	onUnlock: () -> Unit,
){
	Column(modifier = modifier) {
		DoorsTitle(state = vehicle.doorsState.currentState)
		Card{
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
				horizontalArrangement = Arrangement.Center
			){
				DoorButton(
					icon = R.drawable.ic_act_lock,
					isSelected = vehicle.doorsState == DoorsState.LOCKED,
					onClick = onLock
				)
				Spacer(modifier = Modifier.width(8.dp))
				DoorButton(
					icon = R.drawable.ic_act_unlock,
					isSelected = vehicle.doorsState == DoorsState.UNLOCKED,
					onClick = onUnlock
				)
			}
		}
	}
}

@Composable
private fun DoorButton(
	@DrawableRes icon: Int,
	isSelected: Boolean,
	onClick: () -> Unit
){
	val backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
	Box(
		modifier = Modifier
			.size(56.dp)
			.clip(CircleShape)
			.background(backgroundColor)
			.clickable { onClick() }
	){
		Image(
			painter = painterResource(id = icon),
			contentDescription = null,
			modifier = Modifier.align(Alignment.Center)
		)
	}
}

@Composable
private fun DoorsTitle(
	state: Int
){
	Row(verticalAlignment = Alignment.CenterVertically){
		Text(
			text = LocalContext.current.getString(R.string.doors),
			color = MaterialTheme.colors.secondary,
			fontWeight = FontWeight.ExtraBold,
			fontSize = 18.sp
		)
		Spacer(modifier = Modifier
			.padding(horizontal = 6.dp)
			.height(16.dp)
			.width(1.dp)
			.background(MaterialTheme.colors.secondary)
		)
		Text(
			text = LocalContext.current.getString(state),
			color = MaterialTheme.colors.secondary,
			fontSize = 14.sp
		)
	}
}