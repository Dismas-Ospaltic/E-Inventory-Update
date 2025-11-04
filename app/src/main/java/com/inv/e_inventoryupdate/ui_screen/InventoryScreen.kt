package com.inv.e_inventoryupdate.ui_screen


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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.inv.e_inventoryupdate.R
import com.inv.e_inventoryupdate.ui_screen.ui_components.AppStatusBarDynamicColor
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ExclamationCircle
import compose.icons.fontawesomeicons.solid.ShieldAlt
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.baby_powder)
    AppStatusBarDynamicColor(backgroundColor)


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock Update", color = colorResource(id = R.color.raisin_black)) },
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
                        text = "Home screen",
                        color = colorResource(id = R.color.gray01),
                        fontSize = 16.sp
                    )
                }



                repeat(10){
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .clickable {


                            },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            // 🔹 Top: Product Code + Name
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "product code: 85949558",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = colorResource(id = R.color.gray01)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "millan dairy products",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.coral)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // 🔹 Price Section
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Buy Price",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "50",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(id = R.color.bleu_de_france)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "Sell Price",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "60",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(id = R.color.coral)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // 🔹 Other details in a neat row format
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Category",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "dairy products",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.coral)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "Quantity",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "20",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.yellow_green)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "Expected profit",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "200",
                                        fontSize = 14.sp,
                                        color = colorResource(id = R.color.avocado)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

//                            // 🔹 Quantity at bottom center
//                            Text(
//                                text = "Quantity: ${productItem.productQuantity}",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = colorResource(id = R.color.prussian_blue),
//                                modifier = Modifier.align(Alignment.End)
//                            )
                        }
                    }
                }

            }

        }

    }


}






@Preview(showBackground = true)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen(navController = rememberNavController())
}

