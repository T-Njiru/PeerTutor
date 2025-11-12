package com.example.peertutor.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnitsScreen(course: String, onBack: () -> Unit) {
    val unitMap = mapOf(
        "BICS" to listOf("HTML", "CSS", "PROBABILITY", "JAVASCRIPT", "C++", "JAVA"),
        "BBIT" to listOf("NETWORKS", "DATABASES", "ACCOUNTING"),
        "LLB" to listOf("LAW THEORY", "ETHICS"),
    )

    val units = unitMap[course] ?: emptyList()
    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onBack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("$course Units", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        TextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            placeholder = { Text("Search for your course") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F4F6),
                unfocusedContainerColor = Color(0xFFF3F4F6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )


        units
            .filter { it.contains(searchText.value, ignoreCase = true) }
            .forEach { unit ->
                UnitCard(unitName = unit)
            }
    }
}

@Composable
fun UnitCard(unitName: String) {
    val colors = listOf(
        Color(0xFFFF7043), Color(0xFF64B5F6), Color(0xFF9575CD),
        Color(0xFFFFEE58), Color(0xFFD7CCC8), Color(0xFFBA68C8)
    )
    val bgColor = remember { colors.random() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = unitName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}
