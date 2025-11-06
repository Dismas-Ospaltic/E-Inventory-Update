package com.inv.e_inventoryupdate.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inv.e_inventoryupdate.utils.standardDateFormat


@Entity(tableName = "returns")
data class ReturnEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String = standardDateFormat(System.currentTimeMillis()), //DD-MM-YYYY
    val returnId: String,
    val stockId: String,
    val returnQuantity: Int,
    val reason: String,
    val timestamp: Long = System.currentTimeMillis()
)