//package com.inv.e_inventoryupdate.ui_screen.ui_components
//
//
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
////import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import com.inv.e_inventoryupdate.R
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddStockPopUp(
//    onDismiss: () -> Unit
//) {
//    val backgroundColor = colorResource(id = R.color.teal_700)
//
//
//    val context = LocalContext.current
//
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
//
//    var supplierSearch by remember { mutableStateOf("") }
//    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }
//
//    var showSupplierDialog by remember { mutableStateOf(false) }
//
//
//    Dialog(onDismissRequest = { onDismiss() }) {
//        Surface(
//            shape = RoundedCornerShape(16.dp),
//            color = Color.White,
//            tonalElevation = 8.dp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(
//                Modifier
//                    .padding(16.dp)
//                    .imePadding()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Text(text = "Record Stocks Inward", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//
//
//                Text(
//                    text = "Add Stock",
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = colorResource(id = R.color.coral),
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//                // 🔹 Supplier Search Field
//
//
//                OutlinedTextField(
//                    value = supplierSearch,
//                    onValueChange = {
//                        supplierSearch = it
//                        showSupplierDialog = true // open dialog when typing
//                    },
//                    label = { Text("Search Supplier by ID") },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray,
//                        focusedLabelColor = backgroundColor,
//                        cursorColor = backgroundColor
//                    ),
//                    singleLine = true,
//
//                )
//
//
//
//
//                selectedSupplier?.let {
//                    Text(
//                        text = "Selected Supplier: ${it.name} (${it.category})",
//                        color = colorResource(id = R.color.teal_700),
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(bottom = 10.dp)
//                    )
//                }
//
//                // 🔹 Product Info Fields
//
//
//                OutlinedTextField(
//                    value = productCode,
//                    onValueChange = { productCode = it },
//                    label = { Text("Product Code") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray,
//                        focusedLabelColor = backgroundColor,
//                        cursorColor = backgroundColor
//                    ),
//                    singleLine = true,
//
//                )
//
//
//
//                OutlinedTextField(
//                    value = productName,
//                    onValueChange = { productName = it },
//                    label = { Text("Product Name") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray,
//                        focusedLabelColor = backgroundColor,
//                        cursorColor = backgroundColor
//                    ),
//                    singleLine = true,
//
//                    )
//
//
//                OutlinedTextField(
//                    value = category,
//                    onValueChange = { category = it },
//                    label = { Text("Category") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray,
//                        focusedLabelColor = backgroundColor,
//                        cursorColor = backgroundColor
//                    ),
//                    singleLine = true,
//
//                    )
//
//
//
//                // 🔹 Buy Price / Sell Price Row
//                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//
//                    OutlinedTextField(
//                        value = buyPrice,
//                        onValueChange = { buyPrice = it },
//                        label = { Text("Buy Price") },
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(end = 6.dp),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                            focusedBorderColor = backgroundColor,
//                            unfocusedBorderColor = Color.Gray,
//                            focusedLabelColor = backgroundColor,
//                            cursorColor = backgroundColor
//                        ),
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//
//
//
//
//
//                    OutlinedTextField(
//                        value = sellPrice,
//                        onValueChange = { sellPrice = it },
//                        label = { Text("Sell Price") },
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(start = 6.dp),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                            focusedBorderColor = backgroundColor,
//                            unfocusedBorderColor = Color.Gray,
//                            focusedLabelColor = backgroundColor,
//                            cursorColor = backgroundColor
//                        ),
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//                }
//
//                    OutlinedTextField(
//                        value = quantity,
//                        onValueChange = { quantity = it },
//                        label = { Text("Quantity") },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                            focusedBorderColor = backgroundColor,
//                            unfocusedBorderColor = Color.Gray,
//                            focusedLabelColor = backgroundColor,
//                            cursorColor = backgroundColor
//                        ),
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//
//
//
//
//                // Action buttons
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    TextButton(onClick = onDismiss) { Text("Cancel", color = colorResource(id = R.color.coral)) }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Button(
//                        modifier = Modifier
//                            .height(56.dp),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
//                        onClick = {
//
//
//                            onDismiss()
//                        }) {
//                        Text("Save")
//                    }
//                }
//            }
//        }
//    }
//
//
//
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
//
//
//fun generateTimestampBased10DigitNumber(): Long {
//    val timestampPart = (System.currentTimeMillis() / 1000) % 100000 // Last 5 digits of timestamp (seconds)
//    val randomPart = (10000..99999).random() // 5 random digits
//    return "$timestampPart$randomPart".toLong()
//}
//
//
//data class Supplier(
//    val id: String,
//    val name: String,
//    val category: String
//)
//
//package com.inv.e_inventoryupdate.ui_screen.ui_components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import com.inv.e_inventoryupdate.R
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddStockPopUp(onDismiss: () -> Unit) {
//    val backgroundColor = colorResource(id = R.color.teal_700)
//    val context = LocalContext.current
//
//    val suppliers = listOf(
//        Supplier("SUP001", "Fresh Dairy Ltd", "Dairy"),
//        Supplier("SUP002", "Golden Snacks", "Snacks"),
//        Supplier("SUP003", "Healthy Foods Co.", "Groceries"),
//        Supplier("SUP004", "Milano Supplies", "Drinks"),
//        Supplier("SUP005", "Sunrise Traders", "Stationery")
//    )
//
//    var productCode by remember { mutableStateOf("") }
//    var productName by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }
//    var buyPrice by remember { mutableStateOf("") }
//    var sellPrice by remember { mutableStateOf("") }
//    var quantity by remember { mutableStateOf("") }
//    var supplierSearch by remember { mutableStateOf("") }
//    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }
//
//    var expanded by remember { mutableStateOf(false) }
//
//    Dialog(onDismissRequest = { onDismiss() }) {
//        Surface(
//            shape = RoundedCornerShape(16.dp),
//            color = Color.White,
//            tonalElevation = 8.dp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(
//                Modifier
//                    .padding(16.dp)
//                    .imePadding()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Text(
//                    text = "Add Stock",
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = colorResource(id = R.color.coral),
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//
//                // 🔹 Supplier Search Field + Inline Dropdown
//                Box {
//                    OutlinedTextField(
//                        value = supplierSearch,
//                        onValueChange = {
//                            supplierSearch = it
//                            expanded = it.isNotEmpty()
//                        },
//                        label = { Text("Search Supplier by ID") },
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                            focusedBorderColor = backgroundColor,
//                            unfocusedBorderColor = Color.Gray,
//                            focusedLabelColor = backgroundColor,
//                            cursorColor = backgroundColor
//                        ),
//                        singleLine = true,
//                    )
//
//                    DropdownMenu(
//                        expanded = expanded,
//                        onDismissRequest = { expanded = false },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Color.White)
//                    ) {
//                        val filteredSuppliers = suppliers.filter {
//                            it.id.contains(supplierSearch, ignoreCase = true) ||
//                                    it.name.contains(supplierSearch, ignoreCase = true)
//                        }
//
//                        if (filteredSuppliers.isEmpty()) {
//                            DropdownMenuItem(
//                                text = { Text("No supplier found", color = Color.Gray) },
//                                onClick = { expanded = false }
//                            )
//                        } else {
//                            filteredSuppliers.forEach { supplier ->
//                                DropdownMenuItem(
//                                    text = { Text("${supplier.id} - ${supplier.name}") },
//                                    onClick = {
//                                        selectedSupplier = supplier
//                                        supplierSearch = supplier.id
//                                        expanded = false
//                                    }
//                                )
//                            }
//                        }
//                    }
//                }
//
//                selectedSupplier?.let {
//                    Text(
//                        text = "Selected Supplier: ${it.name} (${it.category})",
//                        color = backgroundColor,
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 14.sp
//                    )
//                }
//
//                // 🔹 Product Info Fields
//                OutlinedTextField(
//                    value = productCode,
//                    onValueChange = { productCode = it },
//                    label = { Text("Product Code") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray
//                    )
//                )
//
//                OutlinedTextField(
//                    value = productName,
//                    onValueChange = { productName = it },
//                    label = { Text("Product Name") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray
//                    )
//                )
//
//                OutlinedTextField(
//                    value = category,
//                    onValueChange = { category = it },
//                    label = { Text("Category") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = backgroundColor,
//                        unfocusedBorderColor = Color.Gray
//                    )
//                )
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    OutlinedTextField(
//                        value = buyPrice,
//                        onValueChange = { buyPrice = it },
//                        label = { Text("Buy Price") },
//                        modifier = Modifier.weight(1f).padding(end = 6.dp),
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//                    OutlinedTextField(
//                        value = sellPrice,
//                        onValueChange = { sellPrice = it },
//                        label = { Text("Sell Price") },
//                        modifier = Modifier.weight(1f).padding(start = 6.dp),
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//                }
//
//                OutlinedTextField(
//                    value = quantity,
//                    onValueChange = { quantity = it },
//                    label = { Text("Quantity") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // 🔹 Buttons
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    TextButton(onClick = onDismiss) {
//                        Text("Cancel", color = colorResource(id = R.color.coral))
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Button(
//                        onClick = { onDismiss() },
//                        shape = RoundedCornerShape(12.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
//                    ) {
//                        Text("Save", color = Color.White)
//                    }
//                }
//            }
//        }
//    }
//}
//
//data class Supplier(
//    val id: String,
//    val name: String,
//    val category: String
//)


package com.inv.e_inventoryupdate.ui_screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.inv.e_inventoryupdate.R
import com.inv.e_inventoryupdate.model.SupplierEntity
import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStockPopUp(onDismiss: () -> Unit) {
    val primaryColor = colorResource(id = R.color.teal_700)
    val accentColor = colorResource(id = R.color.coral)




    val supplierViewModel: SupplierViewModel = koinViewModel()
    val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())

    val suppliers = supplierList // this comes from your ViewModel
//    val suppliers = listOf(
//        Supplier("SUP001", "Fresh Dairy Ltd", "Dairy"),
//        Supplier("SUP002", "Golden Snacks", "Snacks"),
//        Supplier("SUP003", "Healthy Foods Co.", "Groceries"),
//        Supplier("SUP004", "Milano Supplies", "Drinks"),
//        Supplier("SUP005", "Sunrise Traders", "Stationery")
//    )

    var supplierSearch by remember { mutableStateOf("") }
//    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }
    var selectedSupplier by remember { mutableStateOf<SupplierEntity?>(null) }

    var productCode by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var buyPrice by remember { mutableStateOf("") }
    var sellPrice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Add Stock",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 🔹 Supplier Search with Smooth Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = supplierSearch,
                        onValueChange = {
                            supplierSearch = it
                            expanded = true
                        },
                        label = { Text("Search Supplier by ID *") },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = primaryColor,
                            unfocusedIndicatorColor = Color.Gray,
                            cursorColor = primaryColor
                        ),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        }
                    )

//                    val filteredSuppliers = suppliers.filter {
//                        it.id.contains(supplierSearch, ignoreCase = true) ||
//                                it.name.contains(supplierSearch, ignoreCase = true)
//                    }

                    val filteredSuppliers = suppliers.filter {
                        it.supplierId.contains(supplierSearch, ignoreCase = true) ||
                                it.supplierName.contains(supplierSearch, ignoreCase = true)
                    }


                    ExposedDropdownMenu(
                        expanded = expanded && supplierSearch.isNotEmpty(),
                        onDismissRequest = { expanded = false },
                    ) {
                        if (filteredSuppliers.isEmpty()) {
                            DropdownMenuItem(
                                text = { Text("No supplier found", color = Color.Gray) },
                                onClick = { expanded = false }
                            )
                        } else {
                            filteredSuppliers.forEach { supplier ->
                                DropdownMenuItem(
                                    text = { Text("${supplier.id} - ${supplier.supplierName}") },
//                                    onClick = {
//                                        supplierSearch = supplier.id
//                                        selectedSupplier = supplier
//                                        expanded = false
//                                    }
                                    onClick = {
                                        supplierSearch = supplier.supplierId
                                        selectedSupplier = supplier
                                        expanded = false
                                    }

                                )
                            }
                        }
                    }
                }

                selectedSupplier?.let {
                    Text(
                        text = "Selected Supplier: ${it.supplierName} (${it.supplierPhone})",
                        color = primaryColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }

                // 🔹 Product Info Inputs
                TextField(
                    value = productCode,
                    onValueChange = { productCode = it },
                    label = { Text("Product Code *") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = primaryColor,
                        unfocusedIndicatorColor = Color.Gray
                    )
                )

                TextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("Product Name*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                TextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextField(
                        value = buyPrice,
                        onValueChange = { buyPrice = it },
                        label = { Text("Buy Price*") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    TextField(
                        value = sellPrice,
                        onValueChange = { sellPrice = it },
                        label = { Text("Sell Price*") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                TextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 🔹 Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel", color = accentColor)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onDismiss() },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Text("Save", color = Color.White)
                    }
                }
            }
        }
    }
}

data class Supplier(
    val id: String,
    val name: String,
    val category: String
)

