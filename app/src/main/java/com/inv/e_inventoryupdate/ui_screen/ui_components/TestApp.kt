//@file:OptIn(ExperimentalMaterial3Api::class)
//
//package com.diwtech.myshop.screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.diwtech.myshop.R
//
//data class Supplier(
//    val id: String,
//    val name: String,
//    val category: String
//)
//
//@Composable
//fun AddStockScreen() {
//
//    // 🔹 Mock supplier list
//    val suppliers = listOf(
//        Supplier("SUP001", "Fresh Dairy Ltd", "Dairy"),
//        Supplier("SUP002", "Golden Snacks", "Snacks"),
//        Supplier("SUP003", "Healthy Foods Co.", "Groceries"),
//        Supplier("SUP004", "Milano Supplies", "Drinks"),
//        Supplier("SUP005", "Sunrise Traders", "Stationery")
//    )
//
//    // 🔹 State variables for stock fields
//    var productCode by remember { mutableStateOf("") }
//    var productName by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }
//    var buyPrice by remember { mutableStateOf("") }
//    var sellPrice by remember { mutableStateOf("") }
//    var quantity by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }
//    var supplierSearch by remember { mutableStateOf("") }
//    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }
//
//    var showSupplierDialog by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .background(colorResource(id = R.color.honeydew)),
//        verticalArrangement = Arrangement.Top
//    ) {
//
//        Text(
//            text = "Add Stock",
//            fontSize = 22.sp,
//            fontWeight = FontWeight.Bold,
//            color = colorResource(id = R.color.russian_violet),
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        // 🔹 Supplier Search Field
//        TextField(
//            value = supplierSearch,
//            onValueChange = {
//                supplierSearch = it
//                showSupplierDialog = true // open dialog when typing
//            },
//            label = { Text("Search Supplier by ID") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 10.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = colorResource(id = R.color.white),
//                focusedIndicatorColor = colorResource(id = R.color.tekhelet),
//                unfocusedIndicatorColor = Color.Gray
//            )
//        )
//
//        selectedSupplier?.let {
//            Text(
//                text = "Selected Supplier: ${it.name} (${it.category})",
//                color = colorResource(id = R.color.tekhelet),
//                fontWeight = FontWeight.Medium,
//                fontSize = 14.sp,
//                modifier = Modifier.padding(bottom = 10.dp)
//            )
//        }
//
//        // 🔹 Product Info Fields
//        TextField(
//            value = productCode,
//            onValueChange = { productCode = it },
//            label = { Text("Product Code") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 6.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = colorResource(id = R.color.white)
//            )
//        )
//
//        TextField(
//            value = productName,
//            onValueChange = { productName = it },
//            label = { Text("Product Name") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 6.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = colorResource(id = R.color.white)
//            )
//        )
//
//        TextField(
//            value = category,
//            onValueChange = { category = it },
//            label = { Text("Category") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 6.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = colorResource(id = R.color.white)
//            )
//        )
//
//        // 🔹 Buy Price / Sell Price Row
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            TextField(
//                value = buyPrice,
//                onValueChange = { buyPrice = it },
//                label = { Text("Buy Price") },
//                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number),
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 6.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = colorResource(id = R.color.white)
//                )
//            )
//            TextField(
//                value = sellPrice,
//                onValueChange = { sellPrice = it },
//                label = { Text("Sell Price") },
//                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number),
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 6.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = colorResource(id = R.color.white)
//                )
//            )
//        }
//
//        // 🔹 Quantity / Date Row
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            TextField(
//                value = quantity,
//                onValueChange = { quantity = it },
//                label = { Text("Quantity") },
//                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number),
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(end = 6.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = colorResource(id = R.color.white)
//                )
//            )
//            TextField(
//                value = date,
//                onValueChange = { date = it },
//                label = { Text("Date (YYYY-MM-DD)") },
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 6.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = colorResource(id = R.color.white)
//                )
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                // Save logic here
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colorResource(id = R.color.tekhelet)
//            ),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("Add Stock", color = colorResource(id = R.color.white), fontSize = 16.sp)
//        }
//    }
//
//    // 🔹 Supplier selection dialog
//    if (showSupplierDialog) {
//        AlertDialog(
//            onDismissRequest = { showSupplierDialog = false },
//            title = { Text("Select Supplier") },
//            text = {
//                LazyColumn {
//                    suppliers.filter {
//                        it.id.contains(supplierSearch, ignoreCase = true)
//                    }.forEach { supplier ->
//                        item {
//                            Text(
//                                text = "${supplier.id} - ${supplier.name}",
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .clickable {
//                                        selectedSupplier = supplier
//                                        supplierSearch = supplier.id
//                                        showSupplierDialog = false
//                                    }
//                                    .padding(vertical = 8.dp),
//                                color = colorResource(id = R.color.bleu_de_france)
//                            )
//                        }
//                    }
//                }
//            },
//            confirmButton = {},
//            containerColor = colorResource(id = R.color.white)
//        )
//    }
//}
