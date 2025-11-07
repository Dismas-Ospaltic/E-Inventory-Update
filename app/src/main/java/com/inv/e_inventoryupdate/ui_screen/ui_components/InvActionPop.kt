package com.inv.e_inventoryupdate.ui_screen.ui_components


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.inv.e_inventoryupdate.R
import com.inv.e_inventoryupdate.navigation_graph.Screen
import com.inv.e_inventoryupdate.utils.standardDateFormat
import com.inv.e_inventoryupdate.viewmodel.ReturnViewModel
import com.inv.e_inventoryupdate.viewmodel.StockViewModel
import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Edit
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.Truck
import compose.icons.fontawesomeicons.solid.TruckLoading
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvActionPop(
    navController: NavController,
    onDismiss: () -> Unit,
    stockId: String,
    supplierId: String,
    date: String,
    status: String
) {

    val backgroundColor = colorResource(id = R.color.coral)

    val context = LocalContext.current

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showReturnDialog by remember { mutableStateOf(false) }
    var showReturnActionDialog by remember { mutableStateOf(false)}
    var showSupplierDetDialog by remember { mutableStateOf(false) }

    val supplierViewModel: SupplierViewModel = koinViewModel()
    val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())
    val stockViewmodel: StockViewModel = koinViewModel()
    val stockUpdates by stockViewmodel.stockList.collectAsState(initial = emptyList())
    val returnViewModel: ReturnViewModel = koinViewModel()

    val currentDate =  System.currentTimeMillis()
    val todayDate = standardDateFormat(currentDate)


    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState()), // Adjust for keyboard
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                Text(text = "Actions", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showSupplierDetDialog = true
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Truck,
                        contentDescription = "supplier",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "View Supplier", fontSize = 16.sp)
                }


                if (status == "inward") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showReturnActionDialog = true
//                            showReturnDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.TruckLoading,
                            contentDescription = "Return Stock",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Return Stock", fontSize = 16.sp)
                    }
                }


  if(date == todayDate) {
      // Delete Button
      Row(
          modifier = Modifier
              .fillMaxWidth()
              .clickable {
                  showDeleteDialog = true
              }
              .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {
          Icon(
              imageVector = FontAwesomeIcons.Regular.TrashAlt,
              contentDescription = "Delete update",
              modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(12.dp))
          Text(text = "Delete update", fontSize = 16.sp)
      }
  }

            }
        }
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Update") },
            text = { Text("Delete Update permanently from the list?") },
            confirmButton = {
                TextButton(onClick = {
                    stockViewmodel.deleteStock(stockId)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                    showDeleteDialog = false
                    onDismiss()
                }) {
                    Text("Delete", color = colorResource(id = R.color.coral))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    onDismiss()
                }) {
                    Text("Cancel", color = colorResource(id = R.color.gray01))
                }
            }
        )
    }




    if (showReturnActionDialog) {
        ReturnStockPop(
            onDismiss = {  showReturnActionDialog = false ;
                onDismiss()},
            stockId = stockId
        )

    }

    if (showSupplierDetDialog) {
        SupplierDetailPop(
            onDismiss = {  showSupplierDetDialog = false ;
                onDismiss()},
            supplierId = supplierId
        )

    }


}