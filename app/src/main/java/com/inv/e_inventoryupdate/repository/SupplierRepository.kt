package com.inv.e_inventoryupdate.repository


import com.inv.e_inventoryupdate.data.local_storage.SupplierDao
import com.inv.e_inventoryupdate.model.SupplierEntity
import kotlinx.coroutines.flow.Flow

class SupplierRepository(private val supplierDao: SupplierDao) {

    fun getAllSuppliers(): Flow<List<SupplierEntity>> = supplierDao.getAllSuppliers()

    suspend fun getAllSuppliersOnce(): List<SupplierEntity> = supplierDao.getAllSuppliersOnce()

    suspend fun insertSupplier(supplier: SupplierEntity) = supplierDao.insertSupplier(supplier)

    suspend fun updateSupplier(supplier: SupplierEntity) = supplierDao.updateSupplier(supplier)

    suspend fun updateSupplierById(
        supplierName: String,
        supplierPhone: String,
        supplierEmail: String,
        supplierAddress: String,
        supplierId: String
    ): Boolean {
        val rowsUpdated = supplierDao.updateSupplierById(
            supplierName,
            supplierPhone,
            supplierEmail,
            supplierAddress,
            supplierId
        ) ?: 0
        return rowsUpdated > 0
    }

    suspend fun deleteSupplierById(supplierId: String): Boolean {
        val rowsDeleted = supplierDao.deleteSupplierById(supplierId)
        return rowsDeleted > 0
    }

    suspend fun updateSupplierStatusById(newStatus: String, supplierId: String): Boolean {
        val rowsUpdated = supplierDao.updateSupplierStatusById(newStatus, supplierId) ?: 0
        return rowsUpdated > 0
    }

    suspend fun getAllSuppliersCount(): Int = supplierDao.getAllSuppliersCount()

    suspend fun getActiveSupplierCount(): Int = supplierDao.getActiveSupplierCount()

    suspend fun getDormantSupplierCount(): Int = supplierDao.getDormantSupplierCount()
}


