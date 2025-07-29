package com.just.track.dialog.addItem.presentation.mapper

import com.just.track.dialog.addItem.presentation.AddItemUIContract
import com.just.track.todo.domain.model.ShoppingItems

fun AddItemUIContract.State.toShoppingItems(): ShoppingItems {
    return ShoppingItems(
        name = name,
        description = description,
        quantity = quantity,
        priority = priority,
        cardColor = cardColor,
        isShopped = false,
        createdAt = createdAt
    )
}
