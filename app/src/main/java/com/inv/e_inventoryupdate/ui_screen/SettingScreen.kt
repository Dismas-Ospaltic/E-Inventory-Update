package com.inv.e_inventoryupdate.ui_screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.sp
import com.inv.e_inventoryupdate.R
import com.inv.e_inventoryupdate.ui_screen.ui_components.AppStatusBarDynamicColor
import com.inv.e_inventoryupdate.ui_screen.ui_components.CardsForSettings
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChalkboardTeacher
import compose.icons.fontawesomeicons.solid.ClipboardList
import compose.icons.fontawesomeicons.solid.ExclamationCircle
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Lock
import compose.icons.fontawesomeicons.solid.ShieldAlt
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.baby_powder)
    AppStatusBarDynamicColor(backgroundColor)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Settings", color = colorResource(id = R.color.raisin_black)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
//                 paddingValues
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding() + 80.dp
                )
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.teal_700))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Manage Your Settings",
                        color = colorResource(id = R.color.gray01),
                        fontSize = 16.sp
                    )
                }
                CardsForSettings(
                    // Privacy Policy Card
                    title = "Privacy Policy",
                    subtitle = "Learn how your information is handled and protected.",
                    icon =  FontAwesomeIcons.Solid.Lock,
                    iconTint = colorResource(id = R.color.coral),
                    onClick = {
                        // Navigate to Privacy Policy screen
                    }
                )

                CardsForSettings(
                    // About Card
                    title = "About",
                    subtitle = "Read about the app and its intellectual property details.",
                    icon =  FontAwesomeIcons.Solid.InfoCircle,
                    iconTint = colorResource(id = R.color.yellow_green),
                    onClick = {
                        // Navigate to About screen
                    }
                )

                CardsForSettings(
                    // App Tour Card
                    title = "App Tour",
                    subtitle = "Replay the onboarding tour to learn how to use the app.",
                    icon =  FontAwesomeIcons.Solid.ChalkboardTeacher,
                    iconTint = colorResource(id = R.color.teal_700),
                    onClick = {
                        // Navigate to App Tour screen
                    }
                )



            }

        }

    }


}






@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}

