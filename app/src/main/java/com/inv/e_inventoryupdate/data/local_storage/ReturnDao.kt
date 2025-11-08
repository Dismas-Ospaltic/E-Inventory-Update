package com.inv.e_inventoryupdate.data.local_storage


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.inv.e_inventoryupdate.model.ReturnEntity
import com.inv.e_inventoryupdate.model.StockEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface ReturnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockReturn(returns: ReturnEntity)

    @Update
    suspend fun updateStockReturn(returns: ReturnEntity)

    @Query("SELECT * FROM returns ORDER BY timestamp DESC")
    fun getAllReturns(): Flow<List<ReturnEntity>>

    @Query("SELECT * FROM returns")
    suspend fun getAllReturnsOnce(): List<ReturnEntity>  // One-time fetch for ViewModel check


//    @Query("SELECT * FROM returns WHERE stockId = :stockId")
//    fun getItemReturnById(stockId: String): Flow<List<ReturnEntity>>

    @Query("SELECT * FROM returns WHERE stockId = :stockId LIMIT 1")
    fun getItemReturnById(stockId: String): Flow<ReturnEntity?>


    // 🔹 Count all returns
    @Query("SELECT COUNT(*) FROM returns")
    suspend fun getAllReturnsCount(): Int

}