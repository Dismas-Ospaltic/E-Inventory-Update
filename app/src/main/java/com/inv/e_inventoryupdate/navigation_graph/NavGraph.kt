package com.inv.e_inventoryupdate.navigation_graph



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.inv.e_inventoryupdate.ui_screen.AboutTheAppScreen
import com.inv.e_inventoryupdate.ui_screen.AppTourScreen
import com.inv.e_inventoryupdate.ui_screen.DashboardOverview
import com.inv.e_inventoryupdate.ui_screen.DormantSupplierScreen
import com.inv.e_inventoryupdate.ui_screen.InventoryScreen
import com.inv.e_inventoryupdate.ui_screen.SettingScreen
import com.inv.e_inventoryupdate.ui_screen.SplashScreen
import com.inv.e_inventoryupdate.ui_screen.SupplierScreen
import com.inv.e_inventoryupdate.viewmodel.AppTourViewModel
import org.koin.androidx.compose.getViewModel


sealed class Screen(val route: String) {
    object Inventory : Screen("inventory")
    object Settings : Screen("settings")

    object Supplier : Screen("supplier")


    object  OverView : Screen("overview")

    object AboutTheApp : Screen("aboutTheApp")

    object DormantSupplier : Screen("dormantSupplier")


    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {

    val mainViewModel: AppTourViewModel = getViewModel()
    val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState(initial = false)

    AnimatedNavHost(
        navController,
        startDestination = Screen.Splash.route,
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
        composable(Screen.DormantSupplier.route) { DormantSupplierScreen(navController) }

        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigate = {
                    when {
                        isOnboardingCompleted -> navController.navigate(Screen.Inventory.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }

                        else -> navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }

                })
        }



        composable(Screen.Onboarding.route) {  AppTourScreen( onCompleteOnboarding = {
            mainViewModel.completeOnboarding()
            navController.navigate(Screen.Inventory.route) {
                popUpTo(Screen.Onboarding.route) { inclusive = true }

            }
        }
        )
        }


    }
}