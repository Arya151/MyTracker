package com.just.track.dialog.addItem.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.just.track.dialog.addItem.presentation.mapper.toShoppingItems
import com.just.track.todo.domain.model.ShoppingItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddItemDialogViewModel : ViewModel() {

    private var _shoppingList = MutableStateFlow<List<ShoppingItems>>(emptyList())
    var shoppingList: StateFlow<List<ShoppingItems>> = _shoppingList

    private var _state = MutableStateFlow<AddItemUIContract.State>(AddItemUIContract.State())
    var state: StateFlow<AddItemUIContract.State> = _state

    init {
        Log.d("Composable", "AddItemDialogViewModel init")
    }
    fun onEvent(event: AddItemUIContract.Event) {
        when (event) {
            is AddItemUIContract.Event.onCardColorChanged -> {
                _state.update {
                    it.copy(
                        cardColor = event.value
                    )
                }
            }

            is AddItemUIContract.Event.onDescriptionChanged -> {
                _state.update {
                    it.copy(
                        description = event.value
                    )
                }
            }

            is AddItemUIContract.Event.onNameChanged -> {
                _state.update {
                    it.copy(
                        name = event.value,
                        isNameError = false
                    )
                }
            }

            is AddItemUIContract.Event.onPriorityChanged -> {
                _state.update {
                    it.copy(
                        priority = event.value
                    )
                }
            }

            is AddItemUIContract.Event.onQuantityChanged -> {
                _state.update {
                    it.copy(
                        quantity = event.value
                    )
                }
            }

            AddItemUIContract.Event.onDismissButtonClicked -> {

            }

            AddItemUIContract.Event.onDoneButtonClicked -> {
                val item = state.value.toShoppingItems()
                _shoppingList.value += item
                _state.update {
                    AddItemUIContract.State()
                }
                Log.d("Composable","shoppingList - ${shoppingList.value}")
            }

            is AddItemUIContract.Event.onNameError -> {
                _state.update {
                    it.copy(
                        isNameError = true
                    )
                }
            }
        }
    }
}
