package com.inv.e_inventoryupdate.repository

import com.inv.e_inventoryupdate.data.local_storage.StockDao
import com.inv.e_inventoryupdate.model.StockEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StockRepository(private val stockDao: StockDao) {

    // 🔹 Get all stock updates (Flow for real-time UI updates)
    fun getAllStockUpdates(): Flow<List<StockEntity>> = stockDao.getAllInvUpdate()

    // 🔹 Get all stock updates once (for one-time fetch)
    suspend fun getAllStockUpdatesOnce(): List<StockEntity> = stockDao.getAllInvUpdateOnce()

    // 🔹 Insert new stock update
    suspend fun insertStock(stock: StockEntity) = stockDao.insertStock(stock)

    // 🔹 Update existing stock
    suspend fun updateStock(stock: StockEntity) = stockDao.updateStock(stock)

    // 🔹 Update stock status by ID
    suspend fun updateStockStatusById(newStatus: String, stockId: String): Boolean {
        val rows = stockDao.updateStockStatusById(newStatus, stockId) ?: 0
        return rows > 0
    }

    // 🔹 Update stock quantity by ID
    suspend fun updateStockQtyById(newQty: Float, stockId: String): Boolean {
        val rows = stockDao.updateStockQtyById(newQty, stockId) ?: 0
        return rows > 0
    }

    // 🔹 Delete stock record by ID
    suspend fun deleteStockById(stockId: String): Boolean {
        val rowsDeleted = stockDao.deleteInvUpdateById(stockId)
        return rowsDeleted > 0
    }

    // 🔹 Count all inventory updates
    suspend fun getAllStockUpdateCount(): Int {
//        val today = "" // You can pass an empty string since query doesn’t use 'today'
        return stockDao.getAllInvUpdateCount()
    }


    fun getAllInvUpdateCountMonthly(month: String): Flow<Int> {
        return stockDao.getAllInvUpdateCountMonthly(month)
            .map { total -> total ?: 0 }  // Convert NULL to 0
    }

    // ✅ Total count of all returned inventory
    suspend fun getAllReturnedInvUpdateCount(): Int {
        return stockDao.getAllReturnedInvUpdateCount()
    }

    // ✅ Monthly count of returned inventory (e.g. "2025-11")
    fun getAllReturnedInvUpdateCountMonthly(month: String): Flow<Int?> {
        return stockDao.getAllReturnedInvUpdateCountMonthly(month)
    }
}
