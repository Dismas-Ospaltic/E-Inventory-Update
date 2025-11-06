//val supplierViewModel: SupplierViewModel = koinViewModel()
//val supplierList by supplierViewModel.suppliers.collectAsState(initial = emptyList())
//
//var supplierSearch by remember { mutableStateOf("") }
//var selectedSupplier by remember { mutableStateOf<SupplierEntity?>(null) }
//var expanded by remember { mutableStateOf(false) }
//
//ExposedDropdownMenuBox(
//expanded = expanded,
//onExpandedChange = { expanded = !expanded }
//) {
//    TextField(
//        value = supplierSearch,
//        onValueChange = {
//            supplierSearch = it
//            expanded = true
//        },
//        label = { Text("Search Supplier by ID *") },
//        modifier = Modifier
//            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
//            .fillMaxWidth(),
//        singleLine = true,
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color.White,
//            unfocusedContainerColor = Color.White,
//            focusedIndicatorColor = primaryColor,
//            unfocusedIndicatorColor = Color.Gray,
//            cursorColor = primaryColor
//        ),
//        trailingIcon = {
//            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//        }
//    )
//
//    val filteredSuppliers = supplierList.filter {
//        it.supplierId.contains(supplierSearch, ignoreCase = true) ||
//                it.supplierName.contains(supplierSearch, ignoreCase = true)
//    }
//
//    ExposedDropdownMenu(
//        expanded = expanded && supplierSearch.isNotEmpty(),
//        onDismissRequest = { expanded = false },
//    ) {
//        if (filteredSuppliers.isEmpty()) {
//            DropdownMenuItem(
//                text = { Text("No supplier found", color = Color.Gray) },
//                onClick = { expanded = false }
//            )
//        } else {
//            filteredSuppliers.forEach { supplierEntity ->
//                DropdownMenuItem(
//                    text = { Text("${supplierEntity.supplierId} - ${supplierEntity.supplierName}") },
//                    onClick = {
//                        supplierSearch = supplierEntity.supplierId
//                        selectedSupplier = supplierEntity
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//
//selectedSupplier?.let {
//    Text(
//        text = "Selected Supplier: ${it.supplierName} (${it.supplierPhone})",
//        color = primaryColor,
//        fontWeight = FontWeight.Medium,
//        fontSize = 14.sp
//    )
//}
