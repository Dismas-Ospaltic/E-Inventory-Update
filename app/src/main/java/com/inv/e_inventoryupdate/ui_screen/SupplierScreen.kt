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
import androidx.compose.ui.draw.clip
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
import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ExclamationCircle
import compose.icons.fontawesomeicons.solid.Search
import compose.icons.fontawesomeicons.solid.ShieldAlt
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupplierScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val backgroundColor = colorResource(id = R.color.baby_powder)
    AppStatusBarDynamicColor(backgroundColor)

//    val itemViewModel: ItemViewModel = koinViewModel()
//    val items by itemViewModel.items.collectAsState()
    val supplierViewModel: SupplierViewModel = koinViewModel()
    val suppliers by supplierViewModel.suppliers.collectAsState(initial = emptyList())



    //**Filter the list based on search query**
    val filteredSuppliers = suppliers.filter {
        it.supplierName.contains(searchQuery, ignoreCase = true) ||
                it.supplierPhone.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Suppliers", color = colorResource(id = R.color.raisin_black)) },
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
                        text = "Search with name or phone",
                        color = colorResource(id = R.color.gray01),
                        fontSize = 16.sp
                    )

                    // Search Field
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text(text = "Search...") },
                        leadingIcon = {

                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Search,
                                contentDescription = "Search Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }


               if (suppliers.isEmpty()){
                   Box(
                       modifier = Modifier.fillMaxSize(),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(
                           text = "Suppliers are not added",
                           color = colorResource(id = R.color.gray01),
                           fontSize = 16.sp
                       )
                   }
               }else if(filteredSuppliers.isEmpty()){

                   Box(
                       modifier = Modifier.fillMaxSize(),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(
                           text = "Search results not found!",
                           color = colorResource(id = R.color.gray01),
                           fontSize = 16.sp
                       )
                   }
               }else{
                   for (index in filteredSuppliers.indices) {
                       val supplier = filteredSuppliers[index]

                           Card(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(horizontal = 4.dp, vertical = 2.dp)
                                   .clickable {


                                   },
                               shape = RoundedCornerShape(4.dp),
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
                                               text = "added on: ${supplier.date}",
                                               fontSize = 13.sp,
                                               fontWeight = FontWeight.Medium,
                                               color = colorResource(id = R.color.gray01)
                                           )
                                           Spacer(modifier = Modifier.height(4.dp))
                                           Text(
                                               text = supplier.supplierName,
                                               fontSize = 18.sp,
                                               fontWeight = FontWeight.SemiBold,
                                               color = colorResource(id = R.color.coral)
                                           )
                                       }
                                   }

                                   Spacer(modifier = Modifier.height(12.dp))

                                   Column {
                                       Text(
                                           text = "email",
                                           fontSize = 12.sp,
                                           color = Color.Gray
                                       )
                                       Text(
                                           text = if (supplier.supplierEmail.isNullOrEmpty()) "Not added" else supplier.supplierEmail,
                                           fontSize = 16.sp,
                                           fontWeight = FontWeight.Bold,
                                           color = if (supplier.supplierEmail.isNullOrEmpty())
                                               colorResource(id = R.color.gray01)
                                           else
                                               colorResource(id = R.color.bleu_de_france)
                                       )

                                   }

                                   Column {
                                       Text(
                                           text = "phone",
                                           fontSize = 12.sp,
                                           color = Color.Gray
                                       )
                                       Text(
                                           text = supplier.supplierPhone,
                                           fontSize = 16.sp,
                                           fontWeight = FontWeight.Bold,
                                           color = colorResource(id = R.color.bleu_de_france)
                                       )
                                   }

                                   Column {
                                       Text(
                                           text = "Address",
                                           fontSize = 12.sp,
                                           color = Color.Gray
                                       )

                                       Text(
                                           text = if (supplier.supplierAddress.isNullOrEmpty()) "Not added" else supplier.supplierAddress,
                                           fontSize = 16.sp,
                                           fontWeight = FontWeight.Bold,
                                           color = if (supplier.supplierAddress.isNullOrEmpty())
                                               colorResource(id = R.color.gray01)
                                           else
                                               colorResource(id = R.color.bleu_de_france)
                                       )

                                   }
                               }
                           }



                   }



               }


//                repeat(10){
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 4.dp, vertical = 2.dp)
//                            .clickable {
//
//
//                            },
//                        shape = RoundedCornerShape(4.dp),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//                        colors = CardDefaults.cardColors(containerColor = Color.White)
//                    ) {
//                        Column(modifier = Modifier.padding(16.dp)) {
//
//                            // 🔹 Top: Product Code + Name
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Column {
//                                    Text(
//                                        text = "added on: 20-05-2024",
//                                        fontSize = 13.sp,
//                                        fontWeight = FontWeight.Medium,
//                                        color = colorResource(id = R.color.gray01)
//                                    )
//                                    Spacer(modifier = Modifier.height(4.dp))
//                                    Text(
//                                        text = "Millan Suppliers",
//                                        fontSize = 18.sp,
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = colorResource(id = R.color.coral)
//                                    )
//                                }
//                            }
//
//                            Spacer(modifier = Modifier.height(12.dp))
//
//                            Column {
//                                Text(
//                                    text = "email",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = "supplier@mullan.com",
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = colorResource(id = R.color.bleu_de_france)
//                                )
//                            }
//
//                            Column {
//                                Text(
//                                    text = "phone",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = "+254756474737",
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = colorResource(id = R.color.bleu_de_france)
//                                )
//                            }
//
//                            Column {
//                                Text(
//                                    text = "Address",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = "Makutano CBD oposite holand Electronics",
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = colorResource(id = R.color.bleu_de_france)
//                                )
//                            }
//                        }
//                    }
//                }

            }

        }

    }


}






@Preview(showBackground = true)
@Composable
fun SupplierScreenPreview() {
    SupplierScreen(navController = rememberNavController())
}

