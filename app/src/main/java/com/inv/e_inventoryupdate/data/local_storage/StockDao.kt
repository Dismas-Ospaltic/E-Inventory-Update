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
    fun getAllItems(): Flow<List<StockEntity>>

    @Query("SELECT * FROM stock_update")
    suspend fun getAllItemsOnce(): List<StockEntity>  // One-time fetch for ViewModel check


    // 🔹 Delete an item by its unique itemId
    @Query("DELETE FROM stock_update WHERE itemId = :itemId")
    suspend fun deleteItemById(itemId: String): Int


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