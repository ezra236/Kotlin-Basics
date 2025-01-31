package com.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AdminActivity : ComponentActivity() {
    private lateinit var dbHelper: AdminDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = AdminDatabaseHelper(this)
        setContent {
            AdminLoginScreen(dbHelper)
        }
    }
}

@Composable
fun AdminLoginScreen(dbHelper: AdminDatabaseHelper) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image - Use the image 'po.jpeg' from the drawable folder
        Image(
            painter = painterResource(id = R.drawable.po), // Image from drawable folder (po.jpeg)
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Content on top of the background image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Admin Login", color = Color.White, style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold))

            Spacer(modifier = Modifier.height(20.dp))

            // Username Input
            Text(text = "Username", color = Color.White, style = TextStyle(fontSize = 20.sp))
            BasicTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(8.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Password Input
            Text(text = "Password", color = Color.White, style = TextStyle(fontSize = 20.sp))
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(8.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(onClick = {
                if (dbHelper.checkCredentials(username, password)) {
                    message = "Successfully Logged In"
                } else {
                    message = "Intruder! Invalid Credentials"
                }
            }) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Display message
            Text(text = message, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdminLoginScreen() {
    val dbHelper = AdminDatabaseHelper(LocalContext.current)
    AdminLoginScreen(dbHelper)
}
