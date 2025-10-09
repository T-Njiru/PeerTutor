package com.example.peertutor.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peertutor.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.peertutor.ui.theme.PeerTutorTheme

@Composable
fun SignUpScreen(
    onCancelClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var role by remember { mutableStateOf("Tutor") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // ✅ enables scrolling
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cancel
        Text(
            text = "Cancel",
            color = Color.Red,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { onCancelClick() }
        )

        Spacer(Modifier.height(16.dp))

        // Welcome Banner
        Box(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
                .width(200.dp)
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome!",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "SIGN UP",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(
            text = "Connect using social networks",
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(Modifier.height(12.dp))

        // Google Sign In
        Image(
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = "Google",
            modifier = Modifier
                .size(40.dp)
                .clickable { /* TODO: Google sign-in */ }
        )

        Spacer(Modifier.height(12.dp))

        // Divider
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
            Text(
                text = " or continue with ",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
        }

        Spacer(Modifier.height(12.dp))

        // Role Buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { role = "Tutor" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (role == "Tutor") Color.Black else Color.LightGray,
                    contentColor = if (role == "Tutor") Color.White else Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Tutor")
            }
            Button(
                onClick = { role = "Tutee" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (role == "Tutee") Color.Black else Color.LightGray,
                    contentColor = if (role == "Tutee") Color.White else Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Tutee")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Text Fields (with resized icons)
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.email),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.lock),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.lock),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Sign Up Button
        Button(
            onClick = { /* TODO: Handle Sign Up */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
        ) {
            Text(
                text = "SIGN UP",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(8.dp))

        // Login link
        Text(
            text = "Already have an account? LOGIN",
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier.clickable { onLoginClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    PeerTutorTheme {
        SignUpScreen()
    }
}
