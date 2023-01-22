package com.simply.assignment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.simply.assignment.data.Vehicle
import com.simply.assignment.ui.components.VehicleImage
import com.simply.assignment.ui.components.VehicleInfo
import com.simply.assignment.ui.theme.lightGrey

@Composable
fun HomeScreen(
	viewModel: HomeViewModel = viewModel()
){
	val vehicles = viewModel.vehicles.collectAsState().value
	if (vehicles.isEmpty()) {
		// show empty state
	} else {
		Column {
			var selectedVehiclePosition by rememberSaveable{
				mutableStateOf(0)
			}
			VehicleInfo(vehicle = vehicles[selectedVehiclePosition])
			Vehicles(
				vehicles = vehicles,
				initialPosition = selectedVehiclePosition,
				onSelectedPosition = { position ->
					selectedVehiclePosition = position
				}
			)
		}
	}
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Vehicles(
	vehicles: List<Vehicle>,
	initialPosition: Int,
	onSelectedPosition: (Int) -> Unit
){
	val pagerState = rememberPagerState(initialPosition)
	onSelectedPosition(pagerState.currentPage)
	ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
		val (background, content) = createRefs()
		Spacer(modifier = Modifier.fillMaxWidth().background(lightGrey).constrainAs(background){
			top.linkTo(parent.top)
			height = Dimension.percent(0.5f)
		})
		HorizontalPager(
			count = vehicles.size,
			state = pagerState,
			modifier = Modifier
				.fillMaxWidth()
				.constrainAs(content) {
					top.linkTo(parent.top)
					height = Dimension.wrapContent
				},
		){ page ->
			VehicleImage(vehicles[page])
		}
	}
}