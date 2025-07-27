package com.just.track.shopping.presentation

import com.just.track.shopping.domain.model.ShoppingItems

// It contains ui state , events, and effects
interface ShoppingUIContract {

    data class State(
        val items: List<ShoppingItems> = emptyList(),
        val isDialogOpen: Boolean = false
    )

    sealed class Event {
        object onFabClicked : Event()
        data class onItemMarkedShopped(val value: Boolean) : Event()
        object onDialogClosed : Event()
    }

    sealed class Effect {
        // data class ShowToast(val message: String) : Effect()
    }

}