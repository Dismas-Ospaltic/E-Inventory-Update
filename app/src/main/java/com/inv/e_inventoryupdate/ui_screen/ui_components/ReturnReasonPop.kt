package com.inv.e_inventoryupdate.ui_screen.ui_components


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun ReturnReasonPop(onDismiss: () -> Unit,
                      stockId: String
) {
    val primaryColor = colorResource(id = R.color.teal_700)
    val accentColor = colorResource(id = R.color.coral)



    val supplierViewModel: SupplierViewModel = koinViewModel()
    val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())
    val supplierDetails by supplierViewModel.supplierDetails.collectAsState()
    val stockViewmodel: StockViewModel = koinViewModel()
    val returnViewModel: ReturnViewModel = koinViewModel()
    val context = LocalContext.current



    val returnItem by returnViewModel.returnItem.collectAsState()

    LaunchedEffect(stockId) {
        returnViewModel.loadReturnItem(stockId)
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
                    text = "Return Reason",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

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


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                if (returnItem != null) {
                                    Text(
                                        text = "Reason: ${returnItem!!.reason}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = colorResource(id = R.color.gray01)
                                    )
                                } else {
                                    Text(
                                        text = "No return record found.",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = colorResource(id = R.color.gray01)
                                    )
                                }
                            }
                        }

                    }
                }


                // 🔹 Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            onDismiss()

                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Text("close", color = Color.White)
                    }
                }
            }
        }
    }
}

