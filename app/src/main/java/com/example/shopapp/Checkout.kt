package com.example.shopapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import android.content.Context
import androidx.compose.ui.platform.LocalContext

class Checkout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckoutScreen()
        }
    }
}

@Composable
fun CheckoutScreen() {
    val output by remember { mutableStateOf("") }

    // Get the context
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_image), // Ensure this image is in res/drawable
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Column for layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center), // Center the Column within the Box
            horizontalAlignment = Alignment.CenterHorizontally, // Align the content inside the Column
            verticalArrangement = Arrangement.Center // Center vertically
        ) {
            // Navigation Options Text
            Text(
                text = "Navigation Options",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Buttons for each action
            Button(
                onClick = { navigateToAdminActivity(context) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Admin")
            }
            Button(
                onClick = { navigateToProductsActivity(context) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Available Products")
            }
            Button(
                onClick = { navigateToLoginActivity(context) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Log-in")
            }
            Button(
                onClick = { navigateToPurchaseActivity(context) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Purchase")
            }
            Button(
                onClick = { exitApp(context) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Exit")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Display the output
            Text(text = output, fontSize = 16.sp, color = Color.White)
        }
    }
}

// Extension functions for navigation
fun navigateToAdminActivity(context: Context) {
    val intent = Intent(context, AdminActivity::class.java)
    context.startActivity(intent)
}

fun navigateToProductsActivity(context: Context) {
    val intent = Intent(context, ProductsActivity::class.java)
    context.startActivity(intent)
}

fun navigateToLoginActivity(context: Context) {
    val intent = Intent(context, LoginActivity::class.java)
    context.startActivity(intent)
}

fun navigateToPurchaseActivity(context: Context) {
    val intent = Intent(context, PurchaseActivity::class.java)
    context.startActivity(intent)
}

fun exitApp(context: Context) {
    // Finish the current activity to close the app
    (context as? ComponentActivity)?.finish()
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckout() {
    CheckoutScreen()
}
