package com.just.track.core.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.just.track.dialog.addItem.presentation.AddItemDialog
import com.just.track.dialog.addItem.presentation.AddItemDialogViewModel
import com.just.track.todo.presentation.ShoppingScreen
import com.just.track.track.presentation.TrackScreen

@Composable
fun BottomNavigationBar(
    currentRout: String?,
    onItemClicked: (Screen) -> Unit
) {
    NavigationBar {
        Screen.bottomNavItems.forEach { screen ->
            NavigationBarItem(
                label = { Text(text = screen.title) },
                icon = {
                    screen.iconRes?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = screen.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                selected = currentRout == screen.route,
                onClick = { onItemClicked(screen) }
            )

        }
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Todo.route,
        modifier = modifier
    ) {

        composable(Screen.Todo.route) {
            ShoppingScreen(
                onAddNewItemClicked = {
                    navController.navigate(Screen.AddItem.route)
                }
            )
        }

        composable(Screen.Track.route) {
            TrackScreen()
        }

        composable(Screen.Profile.route) {

        }

        dialog(Screen.AddItem.route) { navBackStackEntry ->
            // This scopes the ViewModel to the MAIN screen
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(Screen.Todo.route)
            }

            val viewModel: AddItemDialogViewModel = viewModel(parentEntry)

            AddItemDialog(
                viewModel = viewModel,
                onDismiss = { navController.popBackStack() },
                onDone = {
                    navController.popBackStack()
                }
            )
        }

    }

}