package com.inv.e_inventoryupdate.data.local_storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.inv.e_inventoryupdate.model.StockEntity
import com.inv.e_inventoryupdate.model.SupplierEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface SupplierDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSupplier(supplier: SupplierEntity)

    @Update
    suspend fun updateSupplier(supplier: SupplierEntity)

    @Query("SELECT * FROM suppliers ORDER BY timestamp DESC")
    fun getAllSuppliers(): Flow<List<SupplierEntity>>

    @Query("SELECT * FROM suppliers")
    suspend fun getAllSuppliersOnce(): List<SupplierEntity>  // One-time fetch for ViewModel check



    @Query("""
        UPDATE items 
        SET itemName = :itemName, 
            itemPhoto = :itemPhoto, 
            itemCode = :itemCode,
            itemCategory = :itemCategory, 
            itemDescription = :itemDescription,
            itemQuantity = :itemQuantity, 
            manufactureDate = :manufactureDate,
            expiryDate = :expiryDate  
        WHERE itemId = :itemId
    """)
    suspend fun updateSupplierById(
        itemName: String,
        itemPhoto: String,
        itemCode: String,
        itemCategory: String,
        itemDescription: String,
        itemQuantity: Int,
        manufactureDate: String,
        expiryDate: String,
        itemId: String
    ): Int?

    // 🔹 Delete an item by its unique supplierId
    @Query("DELETE FROM suppliers WHERE supplierId = :supplierId")
    suspend fun deleteSupplierById(supplierId: String): Int


//    // 🔹 Count expired products (expiry date before today)
//    @Query("SELECT COUNT(*) FROM items WHERE expiryDate < :today")
//    suspend fun getExpiredProductsCount(today: String): Int
//
//    // 🔹 Count unexpired products (expiry date today or later)
//    @Query("SELECT COUNT(*) FROM items WHERE expiryDate >= :today")
//    suspend fun getUnexpiredProductsCount(today: String): Int
//
////    @Query("UPDATE items SET notified = 1 WHERE itemId = :itemId")
////    suspend fun markAsNotified(itemId: String)
//
//    @Query("SELECT * FROM items WHERE itemId = :itemId")
//    suspend fun getItemById(itemId: String): ItemEntity?
}