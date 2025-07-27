package com.just.track

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.just.track.dialog.addItem.presentation.AddItemDialog
import com.just.track.dialog.addItem.presentation.AddItemDialogViewModel
import com.just.track.shopping.presentation.ShoppingScreen


sealed class Screen(val route: String) {
    object Main : Screen("main")
    object AddItem : Screen("add_item")
    // Add more screens here...
}

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Screen.Main.route
    ) {

        composable(Screen.Main.route) {
            ShoppingScreen(
                onAddNewItemClicked = {
                    navController.navigate(Screen.AddItem.route)
                }
            )
        }

        dialog(Screen.AddItem.route) { navBackStackEntry ->
            // This scopes the ViewModel to the MAIN screen
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(Screen.Main.route)
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