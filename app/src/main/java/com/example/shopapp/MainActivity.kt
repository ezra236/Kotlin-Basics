package com.example.shopapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NinoasServicesScreen()
        }
    }
}

@Composable
fun NinoasServicesScreen() {
    // Access the context using LocalContext.current
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_image), // Ensure your image is in res/drawable
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text at the top
            Text(
                text = "Ninoas Services",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            // Button at the bottom
            Button(
                onClick = {
                    // Navigate to Checkout Activity
                    val intent = Intent(context, Checkout::class.java)
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 32.dp)
            ) {
                Text(text = "Go to Checkout", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNinoasServices() {
    NinoasServicesScreen()
}
