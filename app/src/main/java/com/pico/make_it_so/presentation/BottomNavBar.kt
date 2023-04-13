package com.pico.make_it_so.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.pico.make_it_so.presentation.destinations.Destination

@Composable
fun BottomNavBar(navController: NavController) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    NavigationBar() {
        HomeBottomBarDestinations.values().forEach {
            NavigationBarItem(
                selected = currentDestination == it.direction,
                onClick = { navController.navigate(it.direction.route) { launchSingleTop = true } },
                label = { Text(text = stringResource(id = it.label)) },
                icon = { Icon(painter = painterResource(id = it.icon), contentDescription = null) }
            )
        }
    }
}