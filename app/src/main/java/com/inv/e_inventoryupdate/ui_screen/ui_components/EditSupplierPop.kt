package com.inv.e_inventoryupdate.ui_screen.ui_components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EditSupplierPop(
//    onDismiss: () -> Unit,
//    supplierId: String,
//    supplierName: String,
//    supplierPhone: String,
//    supplierEmail: String,
//    supplierAddress: String,
//) {
//    val primaryColor = colorResource(id = R.color.teal_700)
//    val accentColor = colorResource(id = R.color.coral)
//    val context = LocalContext.current
//
//    var supplierCode by remember { mutableStateOf("") }
//    var supplierName by remember { mutableStateOf("") }
//    var supplierEmail by remember { mutableStateOf("") }
//    var supplierAddress by remember { mutableStateOf("") }
//    var supplierPhone by remember { mutableStateOf("") }
//
//    // 🔹 Error states
//    var codeError by remember { mutableStateOf(false) }
//    var nameError by remember { mutableStateOf(false) }
//    var phoneError by remember { mutableStateOf(false) }
//
//
//    //    val items by itemViewModel.items.collectAsState()
//    val supplierViewModel: SupplierViewModel = koinViewModel()
//    val suppliers by supplierViewModel.suppliers.collectAsState(initial = emptyList())
//
//    val updateResult by supplierViewModel.updateResult.collectAsState()
//
//    LaunchedEffect(updateResult) {
//        updateResult?.let {
//            if (it) {
//                Toast.makeText(context, "Supplier updated successfully", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "Failed to update supplier", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    Dialog(onDismissRequest = onDismiss) {
//        Surface(
//            shape = RoundedCornerShape(16.dp),
//            color = Color.White,
//            tonalElevation = 8.dp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .imePadding()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Text(
//                    text = "Add New Supplier",
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = accentColor,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // 🔹 Supplier Code
//                TextField(
//                    value = supplierCode,
//                    onValueChange = {
//                        supplierCode = it
//                        codeError = false
//                    },
//                    label = { Text("Supplier Id (e.g SUP00220)*") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    isError = codeError,
//                    colors = TextFieldDefaults.colors(
//                        focusedIndicatorColor = primaryColor,
//                        unfocusedIndicatorColor = Color.Gray
//                    )
//                )
//                if (codeError) {
//                    Text("Supplier Id is required", color = Color.Red, fontSize = 12.sp)
//                }
//
//                // 🔹 Supplier Name
//                TextField(
//                    value = supplierName,
//                    onValueChange = {
//                        supplierName = it
//                        nameError = false
//                    },
//                    label = { Text("Supplier Name / Brand*") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    isError = nameError
//                )
//                if (nameError) {
//                    Text("Supplier Name is required", color = Color.Red, fontSize = 12.sp)
//                }
//
//                // 🔹 Supplier Phone
//                TextField(
//                    value = supplierPhone,
//                    onValueChange = {
//                        supplierPhone = it
//                        phoneError = false
//                    },
//                    label = { Text("Supplier Contact Phone*") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true,
//                    isError = phoneError
//                )
//                if (phoneError) {
//                    Text("Supplier Phone is required", color = Color.Red, fontSize = 12.sp)
//                }
//
//                // 🔹 Supplier Email & Address
//                TextField(
//                    value = supplierEmail,
//                    onValueChange = { supplierEmail = it },
//                    label = { Text("Supplier Email") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true
//                )
//
//                TextField(
//                    value = supplierAddress,
//                    onValueChange = { supplierAddress = it },
//                    label = { Text("Supplier Address") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                // 🔹 Action Buttons
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    TextButton(onClick = onDismiss) {
//                        Text("Cancel", color = accentColor)
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Button(
//                        onClick = {
//                            // 🔹 Validate mandatory fields
//                            codeError = supplierCode.isBlank()
//                            nameError = supplierName.isBlank()
//                            phoneError = supplierPhone.isBlank()
//
//                            if (!codeError && !nameError && !phoneError) {
//                                // Save supplier here
//
//
//                                supplierViewModel.insertSupplier(SupplierEntity(
//                                    supplierId = supplierCode,
//                                    supplierName = supplierName,
//                                    supplierPhone = supplierPhone,
//                                    supplierEmail = supplierEmail,
//                                    supplierAddress = supplierAddress
//                                ))
//                                onDismiss()
//                            }
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
//                    ) {
//                        Text("Update", color = Color.White)
//                    }
//                }
//            }
//        }
//    }
//}
//

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSupplierPop(
    onDismiss: () -> Unit,
    supplierId: String,
    supplierName: String,
    supplierPhone: String,
    supplierEmail: String,
    supplierAddress: String,
) {
    val primaryColor = colorResource(id = R.color.teal_700)
    val accentColor = colorResource(id = R.color.coral)
    val context = LocalContext.current

    // ✅ Initialize editable states with passed-in values
    var name by remember { mutableStateOf(supplierName) }
    var phone by remember { mutableStateOf(supplierPhone) }
    var email by remember { mutableStateOf(supplierEmail) }
    var address by remember { mutableStateOf(supplierAddress) }

    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }

    val supplierViewModel: SupplierViewModel = koinViewModel()
    val updateResult by supplierViewModel.updateResult.collectAsState()

    // ✅ Show success/failure toast after update
    LaunchedEffect(updateResult) {
        updateResult?.let {
            if (it) {
                Toast.makeText(context, "Supplier updated successfully", Toast.LENGTH_SHORT).show()
                onDismiss()
            } else {
                Toast.makeText(context, "Failed to update supplier", Toast.LENGTH_SHORT).show()
            }
        }
    }

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
                    text = "Edit Supplier",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Supplier ID (read-only)
                TextField(
                    value = supplierId,
                    onValueChange = {},
                    label = { Text("Supplier ID") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    colors = TextFieldDefaults.colors(
                        disabledTextColor = Color.Gray,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                // Supplier Name
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    label = { Text("Supplier Name / Brand*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = nameError
                )
                if (nameError) Text("Supplier name is required", color = Color.Red, fontSize = 12.sp)

                // Supplier Phone
                TextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        phoneError = false
                    },
                    label = { Text("Supplier Contact Phone*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = phoneError
                )
                if (phoneError) Text("Supplier phone is required", color = Color.Red, fontSize = 12.sp)

                // Supplier Email
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Supplier Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Supplier Address
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Supplier Address") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Buttons
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
                            // ✅ Validation
                            nameError = name.isBlank()
                            phoneError = phone.isBlank()

                            if (!nameError && !phoneError) {
                                supplierViewModel.updateSupplierById(
                                    supplierId = supplierId,
                                    supplierName = name,
                                    supplierPhone = phone,
                                    supplierEmail = email,
                                    supplierAddress = address
                                )
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Text("Update", color = Color.White)
                    }
                }
            }
        }
    }
}


