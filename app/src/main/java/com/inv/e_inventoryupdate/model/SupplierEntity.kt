package com.inv.e_inventoryupdate.model



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inv.e_inventoryupdate.utils.standardDateFormat


@Entity(tableName = "suppliers")
data class SupplierEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String = standardDateFormat(System.currentTimeMillis()), //DD-MM-YYYY
    val supplierId: String,
    val supplierName: String,
    val supplierPhone: String,
    val supplierEmail: String? = null,
    val supplierAddress: String? = null,
    val status: String = "active",
    val timestamp: Long = System.currentTimeMillis()
)