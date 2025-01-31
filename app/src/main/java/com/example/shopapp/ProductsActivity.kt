package com.example.shopapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color // Use Color import for background
import com.example.shopapp.R // Import your R class

class ProductsActivity : ComponentActivity() {
    private lateinit var dbHelper: ProductDatabaseHelper2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = ProductDatabaseHelper2(this)

        setContent {
            // Displaying products from the database
            ProductsScreen(dbHelper)
        }
    }
}

@Composable
fun ProductsScreen(dbHelper: ProductDatabaseHelper2) {
    val products = dbHelper.getAllProducts()

    Log.d("DB_INFO", "Fetched products: ${products.size}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Set background color to black
    ) {
        // Center content inside the Box
        Column(
            modifier = Modifier
                .align(Alignment.Center) // Center content vertically and horizontally
                .padding(16.dp) // Add padding around the content
        ) {
            Text(
                text = "Available Products:",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White), // Set color to white
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (products.isEmpty()) {
                Text(
                    text = "No products available.",
                    style = TextStyle(fontSize = 20.sp, color = Color.White), // Set color to white
                )
            } else {
                for (product in products) {
                    Text(
                        text = "${product.first}: $${product.second}",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal, color = Color.White) // Set color to white
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductsScreen() {
    val dbHelper = ProductDatabaseHelper2(LocalContext.current)
    ProductsScreen(dbHelper)
}
