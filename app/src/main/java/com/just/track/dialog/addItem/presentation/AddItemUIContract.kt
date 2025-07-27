package com.just.track.dialog.addItem.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

interface AddItemUIContract {

    data class State(
        val name: String = "",
        val description: String = "",
        val quantity: Int = 0,
        val priority: Int = 0,
        val cardColor: Int = Color(0xFFB0BEC5).toArgb(),
        val createdAt: Long = 0,

        val isNameError: Boolean = false
    )

    sealed class Event {
        data class onNameChanged(val value: String) : Event()
        data class onNameError(val value: Boolean) : Event()
        data class onDescriptionChanged(val value: String) : Event()
        data class onQuantityChanged(val value: Int) : Event()
        data class onPriorityChanged(val value: Int) : Event()
        data class onCardColorChanged(val value: Int) : Event()

        object onDoneButtonClicked : Event()
        object onDismissButtonClicked : Event()
    }

    sealed class Effect {

    }
}