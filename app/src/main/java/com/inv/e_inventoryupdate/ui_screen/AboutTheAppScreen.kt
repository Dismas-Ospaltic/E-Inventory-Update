package com.inv.e_inventoryupdate.ui_screen

import android.content.Intent
import android.net.Uri
import android.content.pm.PackageManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.inv.e_inventoryupdate.R
import com.inv.e_inventoryupdate.ui_screen.ui_components.AppStatusBarDynamicColor
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleDown
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.AngleUp
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutTheAppScreen(navController: NavController) {
    val context = LocalContext.current
    val backgroundColor = colorResource(id = R.color.baby_powder)
    AppStatusBarDynamicColor(backgroundColor)

    val versionName = try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        "1.0.0"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("About The App", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.AngleLeft,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            Text(
                text = "E-Inventory Update",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.raisin_black)
            )
            Text(
                text = "Version $versionName",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🔹 Expandable sections
            ExpandableCard(
                title = "App Overview",
                description = """
E-Inventory Update helps you record and manage all stock purchases from distributors and suppliers. 
You can track supplier details, monitor stock returns, and view profit estimates — all offline.
                """.trimIndent()
            )

            ExpandableCard(
                title = "Developer",
                description = """
Developed by Systro Dev Lab.
For support or feedback, contact us at: dev@systronicsservices.co.ke
                """.trimIndent(),
                onLinkClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:dev@systronicsservices.co.ke".toUri()
                    }
                    context.startActivity(Intent.createChooser(intent, "Send Email"))
                }
            )

//            ExpandableCard(
//                title = "Legal & Policies",
//                description = """
//• Privacy Policy — Learn how we handle your information.
//• Terms & Conditions — Understand your rights and usage.
//                """.trimIndent()
//            )

            ExpandableCard(
                title = "Intellectual Property Notice",
                description = """
Third-party assets (such as icons and animations) are the property of their respective owners and are used in accordance with their free-to-use licenses.

Systro Dev Lab makes no claim of ownership over any third-party assets utilized within this application. All trademarks, logos, and brand names are the property of their respective owners.

Animations used in the onboarding screens are sourced from LottieFiles. Systro Dev Lab respects and acknowledges all intellectual property rights.
                """.trimIndent()
            )
        }
    }
}

@Composable
fun ExpandableCard(
    title: String,
    description: String,
    onLinkClick: (() -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, colorResource(id = R.color.teal_700)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.raisin_black),
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = if (expanded) FontAwesomeIcons.Solid.AngleUp else FontAwesomeIcons.Solid.AngleDown,
                    contentDescription = null,
                    tint = colorResource(id = R.color.teal_700),
                    modifier = Modifier.size(18.dp)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = Color.Gray
                    )

                    if (onLinkClick != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(onClick = onLinkClick) {
                            Text(
                                text = "Contact Support",
                                color = colorResource(id = R.color.coral),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@Preview(showBackground = true)
@Composable
fun AboutTheAppScreenPreview() {
    AboutTheAppScreen(navController = rememberNavController())
}

