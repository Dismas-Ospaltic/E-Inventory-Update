package com.inv.e_inventoryupdate.navigation_graph



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.ui.Modifier
import com.inv.e_inventoryupdate.ui_screen.AboutTheAppScreen
import com.inv.e_inventoryupdate.ui_screen.DashboardOverview
import com.inv.e_inventoryupdate.ui_screen.InventoryScreen
import com.inv.e_inventoryupdate.ui_screen.SettingScreen
import com.inv.e_inventoryupdate.ui_screen.SupplierScreen


sealed class Screen(val route: String) {
    object Inventory : Screen("inventory")
    object Settings : Screen("settings")

//    object EditProduct : Screen("editProduct/{itemId}") {
//        fun createRoute(itemId: String) = "editProduct/$itemId"
//    }

    object Supplier : Screen("supplier")


    object  OverView : Screen("overview")

    object AboutTheApp : Screen("aboutTheApp")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {


    AnimatedNavHost(
        navController,
        startDestination = Screen.Inventory.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Screen.Inventory.route) { InventoryScreen(navController) }
        composable(Screen.Settings.route) { SettingScreen(navController) }
        composable(Screen.OverView.route) { DashboardOverview(navController) }
        composable(Screen.Supplier.route) { SupplierScreen(navController) }
        composable(Screen.AboutTheApp.route) { AboutTheAppScreen(navController) }
//        composable(Screen.EditProduct.route) { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
//            EditItemScreen(navController, itemId)
//        }







    }
}