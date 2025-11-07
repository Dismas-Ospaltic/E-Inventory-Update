package com.inv.e_inventoryupdate.data.local_storage


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.inv.e_inventoryupdate.model.StockEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStock(stock: StockEntity)

    @Update
    suspend fun updateStock(stock: StockEntity)


    @Query("SELECT * FROM stock_update ORDER BY timestamp DESC")
    fun getAllInvUpdate(): Flow<List<StockEntity>>

    @Query("SELECT * FROM stock_update")
    suspend fun getAllInvUpdateOnce(): List<StockEntity>  // One-time fetch for ViewModel check


    // 🔹 Delete an update by its unique stockId
    @Query("DELETE FROM stock_update WHERE stockId = :stockId")
    suspend fun deleteInvUpdateById(stockId: String): Int


    @Query("""
        UPDATE stock_update 
        SET status= :newStatus
        WHERE stockId = :stockId
    """)
    suspend fun updateStockStatusById(
        newStatus: String,
        stockId: String
    ): Int?



    @Query("""
        UPDATE stock_update 
        SET quantity= :newQty
        WHERE stockId = :stockId
    """)
    suspend fun updateStockQtyById(
        newQty: Float,
        stockId: String
    ): Int?

    // 🔹 Count expired products (expiry date before today)
    @Query("SELECT COUNT(*) FROM stock_update")
    suspend fun getAllInvUpdateCount(): Int

    @Query("SELECT COUNT(*) FROM stock_update WHERE status = 'return'")
    suspend fun getAllReturnedInvUpdateCount(): Int

    @Query("SELECT COUNT(*) FROM stock_update WHERE date LIKE :month || '%' AND status = 'return'")
    fun getAllReturnedInvUpdateCountMonthly(month: String): Flow<Int?>

    @Query("SELECT COUNT(*) FROM stock_update WHERE date LIKE :month || '%'")
    fun getAllInvUpdateCountMonthly(month: String): Flow<Int?>

}