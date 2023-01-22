package com.simply.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.simply.assignment.R
import com.simply.assignment.data.Vehicle

@Composable
fun VehicleInfo(
	vehicle: Vehicle,
	modifier: Modifier = Modifier
) {
	ConstraintLayout(
		modifier = modifier
			.fillMaxWidth()
			.height(56.dp)
			.background(Color.White)
	) {
		val (name, spacer, gasRange) = createRefs()
		Text(
			text = vehicle.name,
			fontWeight = FontWeight.SemiBold,
			fontSize = 20.sp,
			color = MaterialTheme.colors.secondary,
			modifier = Modifier.constrainAs(name) {
				end.linkTo(spacer.start, 8.dp)
				centerVerticallyTo(parent)
			}
		)
		Spacer(modifier = Modifier
			.width(2.dp)
			.height(28.dp)
			.background(MaterialTheme.colors.primary)
			.constrainAs(spacer) {
				centerTo(parent)
				height = Dimension.percent(0.5f)
			}
		)
		Row(
			modifier = Modifier
				.constrainAs(gasRange) {
					start.linkTo(spacer.end, 8.dp)
					centerVerticallyTo(parent)
				},
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				painter = painterResource(id = R.drawable.ic_notif_gas),
				contentDescription = null,
				tint = MaterialTheme.colors.secondary
			)
			Text(
				text = "${vehicle.gasRange}mi",
				color = MaterialTheme.colors.secondary,
				fontWeight = FontWeight.Bold,
				fontSize = 14.sp
			)
		}
	}
}