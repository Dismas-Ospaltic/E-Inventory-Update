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
import com.inv.e_inventoryupdate.model.ReturnEntity
import com.inv.e_inventoryupdate.model.StockEntity
import com.inv.e_inventoryupdate.model.SupplierEntity
import com.inv.e_inventoryupdate.viewmodel.ReturnViewModel
import com.inv.e_inventoryupdate.viewmodel.StockViewModel
import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnStockPop(onDismiss: () -> Unit,
 stockId: String
) {
    val primaryColor = colorResource(id = R.color.teal_700)
    val accentColor = colorResource(id = R.color.coral)


    val supplierViewModel: SupplierViewModel = koinViewModel()
    val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())
    val stockViewmodel: StockViewModel = koinViewModel()
    val stockUpdates by stockViewmodel.stockList.collectAsState(initial = emptyList())
    val returnViewModel: ReturnViewModel = koinViewModel()



    var reasons by remember { mutableStateOf("") }


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
                    text = "Return Stock",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = reasons,
                    onValueChange = { reasons = it },
                    label = { Text("Reason*") },
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
                                reasons.isEmpty() -> {
                                    Toast.makeText(context, "Please enter return Reason", Toast.LENGTH_LONG).show()
                                    return@Button
                                }

                                else -> {

                                    returnViewModel.insertReturn(
                                        ReturnEntity(
                                            returnId = generateTimestampBased10DigitNumberForReturn().toString(),
                                            stockId = stockId,
                                            returnQuantity = 1,
                                            reason = reasons
                                        )
                                    )

                                    stockViewmodel.updateStockStatus(stockId = stockId, newStatus = "return")

                                    onDismiss()
                                    Toast.makeText(context, "Returned", Toast.LENGTH_SHORT).show()
                                }
                            }

                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Text("Return", color = Color.White)
                    }
                }
            }
        }
    }
}




fun generateTimestampBased10DigitNumberForReturn(): Long {
    val timestampPart = (System.currentTimeMillis() / 1000) % 100000
    val randomPart = (10000..99999).random()
    return "$timestampPart$randomPart".toLong()
}

