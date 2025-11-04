package com.inv.e_inventoryupdate.ui_screen.ui_components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.inv.e_inventoryupdate.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Comments
import compose.icons.fontawesomeicons.solid.Plus


@Composable
fun InventoryFab(navController: NavController) {
    val primaryColor = colorResource(id = R.color.coral)
    val whiteColor = colorResource(id = R.color.white)
    var showDialog by remember { mutableStateOf(false) } // State to control popup visibility



    FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = primaryColor,
        modifier = Modifier
            .padding(bottom = 32.dp, end = 16.dp)
            .zIndex(1f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Plus,
                contentDescription = "Add inventory icon",
                tint = whiteColor,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Add to Inventory",
                color = whiteColor,
                fontSize = 16.sp
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun InventoryFabPreview() {
    InventoryFab(navController = rememberNavController())
}