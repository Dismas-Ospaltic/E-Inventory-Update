package com.inv.e_inventoryupdate.ui_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.inv.e_inventoryupdate.R
import kotlinx.coroutines.launch
import com.airbnb.lottie.compose.*
import com.inv.e_inventoryupdate.ui_screen.ui_components.AppStatusBarDynamicColor


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppTourScreen(onCompleteOnboarding: () -> Unit) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val backgroundColor = colorResource(id = R.color.white)
    AppStatusBarDynamicColor(backgroundColor)

    Column(

        modifier = Modifier.fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            .padding(bottom = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Pager
        HorizontalPager(
            state = pagerState,
            count = onboardingPages.size,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageContent(onboardingPages[page])
        }

        // Indicators
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(bottom = 32.dp),
            activeColor = colorResource(id = R.color.coral),
            inactiveColor = Color.Gray
        )

//        // Show only on last page
//        if (pagerState.currentPage == onboardingPages.lastIndex) {
//            Button(
//                onClick = { onCompleteOnboarding() },
//                shape = MaterialTheme.shapes.medium,
//                modifier = Modifier
//                    .padding(bottom = 16.dp)
//                    .fillMaxWidth(0.8f),
////                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
//                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.coral))
//
//            ) {
//                Text("Get Started")
//            }
////            Spacer(modifier = Modifier.height(32.dp)) // Prevent button from being covered
//        } else {
//            // Auto-swipe to next page after a short delay (optional)
//            LaunchedEffect(pagerState.currentPage) {
//                kotlinx.coroutines.delay(2000)
//                scope.launch {
//                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                }
//            }
//        }



        // 🔹 Get Started / Next Button (always visible)
        Button(
            onClick = {
                if (pagerState.currentPage == onboardingPages.lastIndex) {
                    // Finish onboarding
                    onCompleteOnboarding()
                } else {
                    // Move to next page
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.coral))
        ) {
            Text(
                text = if (pagerState.currentPage == onboardingPages.lastIndex) "Get Started" else "Next",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }



    }
}

data class OnboardingPage(
    val lottieAsset: String,
    val title: String,
    val description: String
)

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset(page.lottieAsset))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Adjust height if needed
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = page.title, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = page.description, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
    }
}

// Example pages with local .json Lottie files in assets/
val onboardingPages = listOf(
    OnboardingPage(
        lottieAsset = "add_supplier.json",
        title = "Add Your Suppliers",
        description = "Start by adding your suppliers or distributors with their name, contact, and other details."
    ),
    OnboardingPage(
        lottieAsset = "stock.json",
        title = "Record Stock Purchases",
        description = "Easily record products you receive, including product name, quantity, buy and sell prices."
    ),
    OnboardingPage(
        lottieAsset = "inventory_update.json",
        title = "Track Inventory Updates",
        description = "Stay organized by viewing inventory grouped by date and supplier — know what came in and when."
    ),
    OnboardingPage(
        lottieAsset = "Inventory.json",
        title = "Manage Returns",
        description = "Mark returned items when you send stock back to suppliers and keep your records accurate."
    ),
    OnboardingPage(
        lottieAsset = "report_stock.json",
        title = "View Summary Insights",
        description = "Check total inventory updates, monthly stats, and expected profits to stay on top of your business."
    )
)

