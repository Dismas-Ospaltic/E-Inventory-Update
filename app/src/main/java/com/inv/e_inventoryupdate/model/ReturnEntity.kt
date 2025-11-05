package com.inv.e_inventoryupdate.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inv.e_inventoryupdate.utils.standardDateFormat


@Entity(tableName = "returns")
data class ReturnEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String = standardDateFormat(System.currentTimeMillis()), //DD-MM-YYYY
    val supplierId: String,
    val stockId: String,
    val productName: String,
    val buyPrice: Float,
    val sellPrice: Float,
    val productCode: String,
    val quantity: Int,
    val status: String = "inward",
    val notes: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)