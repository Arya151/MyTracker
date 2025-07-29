package com.just.track.todo.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.just.track.dialog.addItem.presentation.AddItemDialogViewModel

@Composable
fun ShoppingScreen(
    onAddNewItemClicked: () -> Unit,
    viewModel: AddItemDialogViewModel = viewModel()
) {

    val shoppingItems = viewModel.shoppingList.collectAsState().value

    LaunchedEffect(shoppingItems) {
        Log.d("Composable", "Got items: $shoppingItems")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(shoppingItems.size) { index ->
                val item = shoppingItems[index]
                ItemCard(
                    item
                ) {
                    item.isShopped
                }

            }
        }

        FloatingActionButton(
            onClick = onAddNewItemClicked,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Item"
            )
        }
    }


}