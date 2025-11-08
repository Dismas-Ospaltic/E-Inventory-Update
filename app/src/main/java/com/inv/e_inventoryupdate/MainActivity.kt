package com.inv.e_inventoryupdate

import com.inv.e_inventoryupdate.navigation_graph.AppNavHost
import com.inv.e_inventoryupdate.navigation_graph.Screen
import com.inv.e_inventoryupdate.ui_screen.ui_components.InventoryFab
import compose.icons.fontawesomeicons.solid.ChartBar
import compose.icons.fontawesomeicons.solid.TruckMoving

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.inv.e_inventoryupdate.ui.theme.EInventoryUpdateTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            EInventoryUpdateTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    EInventoryUpdateTheme {
//        Greeting("Android")
//    }
//}




//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.d12.expirymonitor.ui.theme.ExpiryMonitorTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ExpiryMonitorTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ExpiryMonitorTheme {
//        Greeting("Android")
//    }
//}





import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.inv.e_inventoryupdate.ui_screen.ui_components.SupplierFab
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChartLine
import compose.icons.fontawesomeicons.solid.ClipboardList
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.Warehouse
import java.util.Locale

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure full-screen layout
        WindowCompat.setDecorFitsSystemWindows(window, false)


        // ✅ Enable edge-to-edge layout with transparent system bars
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )

        setContent {
            val navController = rememberAnimatedNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Define screens where the bottom bar should be hidden
            val hideBottomBarScreens = listOf(Screen.AboutTheApp.route, Screen.DormantSupplier.route)

            Scaffold(
                bottomBar = {
//                    BottomNavigationBar(navController)
                    if (currentRoute !in hideBottomBarScreens) {
                        BottomNavigationBar(navController)
                    }
                },
                floatingActionButton = {
                    if (currentRoute == Screen.Inventory.route) { // Show FAB only on inventory
                        InventoryFab(navController = navController)
                    }

                    if (currentRoute == Screen.Supplier.route) { // Show FAB only on supplier
                        SupplierFab(navController = navController)
                    }
//
                }

            ) { paddingValues ->
                AppNavHost(navController,
                    Modifier
                        .padding(paddingValues)
                        .windowInsetsPadding(WindowInsets.systemBars))
            }
        }
    }





}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val screens = listOf(Screen.Inventory,Screen.OverView,Screen.Supplier,Screen.Settings)

    val backgroundColor = colorResource(id = R.color.bottom_bar_background)
//    val selectedColor = colorResource(id = R.color.tab_selected)
    val selectedColor = colorResource(id = R.color.yellow_green)
    val unselectedColor = colorResource(id = R.color.tab_unselected)
    val tabIndicatorColor = colorResource(id = R.color.tab_indicator)

    Column{
        // Top Divider
        HorizontalDivider(
            thickness = 1.dp, // Adjust thickness as needed
            color =  colorResource(id = R.color.border_color)
        )
        NavigationBar(
            containerColor = backgroundColor
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination == screen.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (currentDestination != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(Screen.Inventory.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {

                        when (screen) {
                            is Screen.Inventory -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.ClipboardList,
                                    contentDescription = "Inventory page",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }// cog icon from font awesome icons
                            is Screen.Settings -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.Cog,
                                    contentDescription = "setting",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            is Screen.Supplier -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.TruckMoving,
                                    contentDescription = "supplier",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            is Screen.OverView -> {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.ChartLine,
                                    contentDescription = "overview",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            else ->  {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.Home,
                                    contentDescription = "home",
                                    tint = if (isSelected) selectedColor else unselectedColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }// Home icon from font awesome icons  // Fallback icon
                        }

                    },
                    label = {
                        Text(
                            text = screen.route.replaceFirstChar { it.titlecase(Locale.ROOT) },
                            color = if (isSelected) selectedColor else unselectedColor // Apply custom colors
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        selectedTextColor = selectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = tabIndicatorColor // Change the background color of selected tab
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}