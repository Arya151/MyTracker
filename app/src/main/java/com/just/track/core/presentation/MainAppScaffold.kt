package com.just.track.core.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScaffold(
    navController: NavHostController
) {

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val currentScreen = Screen.bottomNavItems.find { it.route == currentRoute }
    val showBottomBar =  true //Screen.bottomNavItems.any { it.route == currentRoot }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentScreen?.title ?: "My Tracker") },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                )
            )
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    currentRout = currentRoute,
                    onItemClicked = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(navController, modifier = Modifier.padding(innerPadding))
    }
}