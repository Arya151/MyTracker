package com.just.track.shopping.domain.model

data class ShoppingItems(
    val name: String,
    val description: String,
    val quantity: Int,
    val priority: Int,
    val cardColor: Int,
    val isShopped: Boolean,
    val createdAt: Long
)
