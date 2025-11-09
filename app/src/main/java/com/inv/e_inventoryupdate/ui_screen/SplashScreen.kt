package com.inv.e_inventoryupdate.ui_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inv.e_inventoryupdate.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    // Use a simple delay to simulate a splash screen
    LaunchedEffect(Unit) {
        delay(2000) // Show splash screen for 2 seconds
        onNavigate() // Navigate after delay
    }

    val backgroundColor = colorResource(id = R.color.teal_700)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.inventory_app),
                contentDescription = "App icon",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to E-Inventory Update",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = androidx.compose.ui.graphics.Color.White // white text for contrast
            )
        }
    }
}
