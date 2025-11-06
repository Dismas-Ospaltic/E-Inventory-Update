package com.inv.e_inventoryupdate.repository


import com.inv.e_inventoryupdate.data.local_storage.ReturnDao
import com.inv.e_inventoryupdate.model.ReturnEntity
import kotlinx.coroutines.flow.Flow

class ReturnRepository(private val returnDao: ReturnDao) {

    fun getAllReturns(): Flow<List<ReturnEntity>> = returnDao.getAllReturns()

    suspend fun getAllReturnsOnce(): List<ReturnEntity> = returnDao.getAllReturnsOnce()

    suspend fun insertStockReturn(returns: ReturnEntity) = returnDao.insertStockReturn(returns)

    suspend fun updateStockReturn(returns: ReturnEntity) = returnDao.updateStockReturn(returns)

    fun getItemReturnById(stockId: String): Flow<List<ReturnEntity>> =
        returnDao.getItemReturnById(stockId)

    suspend fun getAllReturnsCount(): Int = returnDao.getAllReturnsCount()
}
