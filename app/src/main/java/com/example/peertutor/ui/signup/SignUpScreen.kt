package com.example.peertutor.ui.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peertutor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun SignUpScreen(
    onCancelClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    var role by remember { mutableStateOf("Tutor") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cancel",
            color = Color.Red,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { onCancelClick() }
        )

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
                .width(200.dp)
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Welcome!", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(8.dp))
        Text("SIGN UP", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("Connect using social networks", color = Color.Gray, fontSize = 12.sp)
        Spacer(Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = "Google",
            modifier = Modifier
                .size(40.dp)
                .clickable { /* TODO: Add Google Sign-In */ }
        )

        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
            Text(" or continue with ", fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
            Divider(modifier = Modifier.weight(1f), color = Color.LightGray)
        }

        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { role = "Tutor" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (role == "Tutor") Color.Black else Color.LightGray,
                    contentColor = if (role == "Tutor") Color.White else Color.Black
                )
            ) { Text("Tutor") }

            Button(
                onClick = { role = "Tutee" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (role == "Tutee") Color.Black else Color.LightGray,
                    contentColor = if (role == "Tutee") Color.White else Color.Black
                )
            ) { Text("Tutee") }
        }

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") },
            leadingIcon = { Image(painter = painterResource(R.drawable.account), contentDescription = null, modifier = Modifier.size(20.dp)) },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") },
            leadingIcon = { Image(painter = painterResource(R.drawable.email), contentDescription = null, modifier = Modifier.size(20.dp)) },
            modifier = Modifier.fillMaxWidth().height(56.dp)
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") },
            leadingIcon = { Image(painter = painterResource(R.drawable.lock), contentDescription = null, modifier = Modifier.size(20.dp)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        )

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") },
            leadingIcon = { Image(painter = painterResource(R.drawable.lock), contentDescription = null, modifier = Modifier.size(20.dp)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        )

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                if (password != confirmPassword) {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    isLoading = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid ?: ""
                                val userMap = mapOf(
                                    "fullName" to fullName,
                                    "email" to email,
                                    "role" to role,
                                    "course" to "",
                                    "units" to listOf<String>(),
                                    "lessons" to listOf<String>()
                                )
                                database.child("users").child(userId).setValue(userMap)
                                Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                onSignUpSuccess()
                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
        ) {
            Text("SIGN UP", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        if (isLoading) {
            Spacer(Modifier.height(8.dp))
            CircularProgressIndicator()
        }

        Spacer(Modifier.height(8.dp))
        Text("Already have an account? LOGIN", color = Color.Black, fontSize = 12.sp,
            modifier = Modifier.clickable { onLoginClick() })
    }
}
