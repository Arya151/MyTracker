package com.just.track.core.presentation

import com.just.track.R


sealed class Screen(val route: String, val title: String = "", val iconRes: Int? = null) {

    // Bottom Nav Screens
    object Todo : Screen("todo", "ToDo", R.drawable.ic_todo)
    object Track : Screen("track", "Track", R.drawable.ic_bar_chart)
    object Profile : Screen("profile", "Profile", R.drawable.ic_user)

    // Other Screens
    object Other : Screen("other")
    object AddItem : Screen("add_item")

    companion object {
        val bottomNavItems = listOf(Todo, Track, Profile)
    }
}