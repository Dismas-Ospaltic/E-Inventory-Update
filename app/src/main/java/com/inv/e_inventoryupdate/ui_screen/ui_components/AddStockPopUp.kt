//package com.inv.e_inventoryupdate.ui_screen.ui_components


package com.inv.e_inventoryupdate.ui_screen.ui_components

import android.widget.Toast
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
import com.inv.e_inventoryupdate.model.StockEntity
import com.inv.e_inventoryupdate.model.SupplierEntity
import com.inv.e_inventoryupdate.viewmodel.StockViewModel
import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStockPopUp(onDismiss: () -> Unit) {
    val primaryColor = colorResource(id = R.color.teal_700)
    val accentColor = colorResource(id = R.color.coral)




    val supplierViewModel: SupplierViewModel = koinViewModel()
    val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())
    val stockViewmodel: StockViewModel = koinViewModel()
    val stockUpdates by stockViewmodel.stockList.collectAsState(initial = emptyList())


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
    var notes by remember { mutableStateOf("") }
    var buyPrice by remember { mutableStateOf("") }
    var sellPrice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

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

                TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes(optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
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
                        onClick = {


                            when {

//                                selectedSupplier!!.supplierId.isNullOrEmpty() ->{
//                                    Toast.makeText(context, "Please select supplier", Toast.LENGTH_LONG).show()
//                                    return@Button
//                                }


                                selectedSupplier == null || selectedSupplier?.supplierId.isNullOrEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Please select a supplier",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    return@Button
                                }

                                productCode.isEmpty() -> {
                                    Toast.makeText(context, "Please enter product barcode/code", Toast.LENGTH_LONG).show()
                                    return@Button
                                }

                                productName.isEmpty() -> {
                                    Toast.makeText(context, "Please enter product name", Toast.LENGTH_LONG).show()
                                    return@Button
                                }

                                sellPrice.isEmpty() -> {
                                    Toast.makeText(context, "Please enter product sell price", Toast.LENGTH_LONG).show()
                                    return@Button
                                }

                                buyPrice.isEmpty() -> {
                                    Toast.makeText(context, "Please enter product buy price", Toast.LENGTH_LONG).show()
                                    return@Button
                                }

                                quantity.isEmpty() -> {
                                    Toast.makeText(context, "Please enter quantity", Toast.LENGTH_LONG).show()
                                    return@Button
                                }



                                else -> {

                                    stockViewmodel.insertStock(
                                        StockEntity(
//                                            supplierId = selectedSupplier!!.supplierId ,
                                            supplierId = selectedSupplier?.supplierId ?: "",
                                            stockId = generateTimestampBased10DigitNumberForStock().toString(),
                                            productName = productName,
                                            category = category,
                                            buyPrice = buyPrice.toFloat(),
                                            sellPrice = sellPrice.toFloat(),
                                            productCode = productCode,
                                            quantity = quantity.toInt(),
                                            notes = notes
                                        )
                                    )


                                    onDismiss()
                                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                }
                            }







                             },
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


fun generateTimestampBased10DigitNumberForStock(): Long {
    val timestampPart = (System.currentTimeMillis() / 1000) % 100000
    val randomPart = (10000..99999).random()
    return "$timestampPart$randomPart".toLong()
}

