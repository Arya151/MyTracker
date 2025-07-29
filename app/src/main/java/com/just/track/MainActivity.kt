package com.just.track

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.just.track.core.presentation.MainAppScaffold
import com.just.track.ui.theme.MyTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTrackerTheme {
                val navController = rememberNavController()
                MainAppScaffold(navController = navController)
            }
        }
    }
}
