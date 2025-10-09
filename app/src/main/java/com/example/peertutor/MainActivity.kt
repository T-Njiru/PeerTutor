package com.example.peertutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.peertutor.navigation.NavGraph
import com.example.peertutor.ui.theme.PeerTutorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeerTutorTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
