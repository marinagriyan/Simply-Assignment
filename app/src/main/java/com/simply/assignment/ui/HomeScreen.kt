package com.simply.assignment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.simply.assignment.data.Vehicle
import com.simply.assignment.ui.components.Doors
import com.simply.assignment.ui.components.Engine
import com.simply.assignment.ui.components.UnlockActionDialog
import com.simply.assignment.ui.components.VehicleImage
import com.simply.assignment.ui.components.VehicleInfo
import com.simply.assignment.ui.theme.lightestGrey

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
	val vehicles = viewModel.uiState.collectAsState().value.vehicles
	if (vehicles.isEmpty()) {
		// show empty state
	} else {
		var showUnlockVehicleDialog: Vehicle? by rememberSaveable { mutableStateOf(null) }
		showUnlockVehicleDialog?.let { vehicle ->
			UnlockActionDialog(
				vehicle = vehicle,
				onDismiss = { showUnlockVehicleDialog = null }
			){
				showUnlockVehicleDialog = null
				viewModel.unlockVehicle(vehicle.id)
			}
		}
		Column {
			var selectedVehiclePosition by rememberSaveable{
				mutableStateOf(0)
			}
			val selected = vehicles[selectedVehiclePosition]
			VehicleInfo(vehicle = selected.vehicle)
			Vehicles(
				vehicles = vehicles,
				initialPosition = selectedVehiclePosition
			) { position ->
				selectedVehiclePosition = position
			}
			Spacer(modifier = Modifier
				.fillMaxWidth()
				.height(8.dp)
			)
			VehicleActions(
				vehicleUIState = selected,
				onLock = {
					viewModel.lockVehicle(selected.vehicle.id)
				},
				onUnlock = {
					showUnlockVehicleDialog = selected.vehicle
				}
			)
		}
	}
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Vehicles(
	vehicles: List<VehicleUIState>,
	initialPosition: Int,
	onSelectedPosition: (Int) -> Unit
){
	val pagerState = rememberPagerState(initialPosition)
	LaunchedEffect(pagerState.currentPage) {
		onSelectedPosition(pagerState.currentPage)
	}
	
	ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
		val (background, content) = createRefs()
		Spacer(modifier = Modifier
			.fillMaxWidth()
			.background(lightestGrey)
			.constrainAs(background) {
				top.linkTo(parent.top)
				height = Dimension.percent(0.5f)
			})
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.constrainAs(content) {
					top.linkTo(parent.top)
					height = Dimension.wrapContent
				}
		){
			HorizontalPager(
				count = vehicles.size,
				state = pagerState,
			){ page ->
				VehicleImage(vehicles[page].vehicle)
			}
			Spacer(modifier = Modifier
				.fillMaxWidth()
				.height(16.dp)
			)
			HorizontalPagerIndicator(
				pagerState = pagerState,
				activeColor = MaterialTheme.colors.primary,
				inactiveColor = MaterialTheme.colors.onSecondary,
				indicatorShape = RectangleShape,
				indicatorWidth = 36.dp,
				indicatorHeight = 3.dp,
				spacing = 6.dp,
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)
		}
	}
}

@Composable
private fun VehicleActions(
	vehicleUIState: VehicleUIState,
	onLock: () -> Unit,
	onUnlock: () -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Doors(
			vehicle = vehicleUIState.vehicle,
			modifier = Modifier
				.weight(1f)
				.padding(8.dp),
			isDoorStateUpdating = vehicleUIState.isDoorStateUpdating,
			onLock = onLock,
			onUnlock = onUnlock
		)
		Engine(
			vehicle = vehicleUIState.vehicle,
			modifier = Modifier
				.weight(1f)
				.padding(8.dp),
			onStart = {},
			onStop = {}
		)
	}
}