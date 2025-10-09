package com.example.peertutor.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peertutor.R
import com.example.peertutor.ui.theme.PeerTutorTheme

@Composable
fun SplashScreen(onGetStartedClick: () -> Unit = {}, onLoginClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00004D)), // deep navy blue
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "PeerTutor Logo",
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 16.dp)
            )

            // App Name
            Text(
                text = "PEERTUTOR",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "LEARNING TOGETHER",
                color = Color(0xFFB3C8FF),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))

            // Already have an account?
            Text(
                buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                        append("Click here.")
                    }
                },
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable { onLoginClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Get Started Button
            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "GET STARTED",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00004D)
@Composable
fun SplashScreenPreview() {
    PeerTutorTheme {
        SplashScreen()
    }
}
