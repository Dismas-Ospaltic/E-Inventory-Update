package com.inv.e_inventoryupdate.data.local_storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
        UPDATE suppliers 
        SET supplierName = :supplierName, 
            supplierPhone = :supplierPhone, 
            supplierEmail = :supplierEmail,
            supplierAddress = :supplierAddress
        WHERE supplierId = :supplierId
    """)
    suspend fun updateSupplierById(
        supplierName: String,
        supplierPhone: String,
        supplierEmail: String,
        supplierAddress: String,
        supplierId: String
    ): Int?

    // 🔹 Delete an item by its unique supplierId
    @Query("DELETE FROM suppliers WHERE supplierId = :supplierId")
    suspend fun deleteSupplierById(supplierId: String): Int


    @Query("""
        UPDATE suppliers 
        SET status= :newStatus
        WHERE supplierId = :supplierId
    """)
    suspend fun updateSupplierStatusById(
        newStatus: String,
        supplierId: String
    ): Int?

    // 🔹 Count no of suppliers
    @Query("SELECT COUNT(*) FROM suppliers")
    suspend fun getAllSuppliersCount(): Int


    // 🔹 Count no of active suppliers
    @Query("SELECT COUNT(*) FROM suppliers WHERE status ='active'")
    suspend fun getActiveSupplierCount(): Int

    // 🔹 Count no of dormant suppliers
    @Query("SELECT COUNT(*) FROM suppliers WHERE status ='dormant'")
    suspend fun getDormantSupplierCount(): Int


}