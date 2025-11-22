package com.example.peertutor.ui.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peertutor.R
import com.example.peertutor.ui.theme.PeerTutorTheme

@Composable
fun DashboardScreen() {

    val username = "Ngina Kamau"
    val units = listOf("HTML", "CSS", "Javascript", "PHP", "Java", "C#")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FD))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // HEADER
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Good morning,", fontSize = 14.sp, color = Color.Gray)
                    Text(username, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFCCE5FF))
                            .padding(8.dp)
                    )
                }
            }
        }

        // SEARCH
        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF2196F3)
                )
            )
        }

        // COURSES CARD
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "Available Courses:",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))
                        ) {
                            Text("Get Started", color = Color.White)
                        }
                    }
                    Image(
                        painter = painterResource(R.drawable.account),
                        contentDescription = null,
                        modifier = Modifier.size(90.dp)
                    )
                }
            }
        }

        // TITLE
        item {
            Text(
                "Your Units",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // GRID - FIXED HEIGHT (IMPORTANT!)
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // 🔥 FIXED HEIGHT = NO SCROLL CONFLICT
            ) {
                items(units) { unit ->
                    val color = when (unit) {
                        "HTML" -> Color(0xFFFF7043)
                        "CSS" -> Color(0xFF64B5F6)
                        "Javascript" -> Color(0xFFFFEE58)
                        "PHP" -> Color(0xFF9575CD)
                        "Java" -> Color(0xFFD7CCC8)
                        "C#" -> Color(0xFFBA68C8)
                        else -> Color.LightGray
                    }

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(unit, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    PeerTutorTheme {
        DashboardScreen()
    }
}
