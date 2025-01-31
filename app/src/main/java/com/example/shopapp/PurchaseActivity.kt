package com.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PurchaseActivity : ComponentActivity() {
    private lateinit var dbHelper: ProductDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = ProductDatabaseHelper(this)
        setContent {
            PurchaseScreen(dbHelper)
        }
    }
}

@Composable
fun PurchaseScreen(dbHelper: ProductDatabaseHelper) {
    var productName by remember { mutableStateOf("") }
    var totalPrice by remember { mutableStateOf(0f) }
    var message by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_image), // Ensure image is in res/drawable
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xAA000000)), // Semi-transparent background
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Purchase Products", fontSize = 24.sp, color = Color.White)

            Spacer(modifier = Modifier.height(10.dp))

            // Product Name Input
            Text(text = "Enter Product Name", color = Color.White)
            BasicTextField(
                value = productName,
                onValueChange = { productName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp) // Increased height of the input field
                    .padding(16.dp) // Increased padding for larger input area
                    .background(Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Purchase Button
            Button(
                onClick = {
                    val price = dbHelper.getProductPrice(productName)
                    if (price != null) {
                        totalPrice += price
                        message = "$productName added! Total Price: $$totalPrice"
                    } else {
                        message = "Product not found!"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add to Cart")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Display Message
            Text(text = message, color = Color.White)

            Spacer(modifier = Modifier.height(10.dp))

            // Checkout Button
            Button(
                onClick = {
                    message = "Total Price: $$totalPrice\nThank you for shopping!"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Checkout")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPurchaseScreen() {
    val dbHelper = ProductDatabaseHelper(LocalContext.current)
    PurchaseScreen(dbHelper)
}
