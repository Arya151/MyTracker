package com.just.track.todo.presentation

import com.just.track.todo.domain.model.ShoppingItems

// It contains ui state , events, and effects
interface TodoUIContract {

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