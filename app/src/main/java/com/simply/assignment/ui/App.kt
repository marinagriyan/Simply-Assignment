package com.simply.assignment.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.simply.assignment.ui.navigation.ScreenTabs

@Composable
fun App() {
	val tabs = remember { ScreenTabs.values() }
	val navController = rememberNavController()
	Scaffold(
		backgroundColor = MaterialTheme.colors.background,
		bottomBar = { BottomBar(navController = navController, tabs) }
	) { innerPadding ->
		NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
	}
}

@Composable
fun NavigationGraph(
	modifier: Modifier = Modifier,
	navController: NavHostController
) {
	NavHost(navController, startDestination = ScreenTabs.HOME.route, modifier = modifier) {
		composable(ScreenTabs.HOME.route) {
			HomeScreen()
		}
		composable(ScreenTabs.VEHICLE.route) {
		
		}
		composable(ScreenTabs.LOCATION.route) {
		
		}
		composable(ScreenTabs.SETTINGS.route) {
		
		}
	}
}

@Composable
fun BottomBar(navController: NavController, tabs: Array<ScreenTabs>) {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route ?: ScreenTabs.HOME.route
	BottomNavigation(backgroundColor = Color.White) {
		tabs.forEach { tab ->
			BottomNavigationItem(
				icon = { Icon(painterResource(tab.icon), contentDescription = null) },
				label = { Text(stringResource(tab.title)) },
				selected = currentRoute == tab.route,
				onClick = {
					if (tab.route != currentRoute) {
						navController.navigate(tab.route) {
							popUpTo(navController.graph.startDestinationId) {
								saveState = true
							}
							launchSingleTop = true
							restoreState = true
						}
					}
				},
				selectedContentColor = MaterialTheme.colors.primary,
				unselectedContentColor = MaterialTheme.colors.secondary,
				modifier = Modifier.navigationBarsPadding()
			)
		}
	}
}