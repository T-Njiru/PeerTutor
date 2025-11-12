package com.example.peertutor.ui.dashboard

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DashboardNavigation() {
    var selectedCourse by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        if (selectedCourse == null) {
            CoursesScreen(onCourseSelected = { course ->
                selectedCourse = course
            })
        } else {
            UnitsScreen(course = selectedCourse!!, onBack = { selectedCourse = null })
        }
    }
}
