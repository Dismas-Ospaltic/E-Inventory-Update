package com.inv.e_inventoryupdate.data.local_storage

import com.inv.e_inventoryupdate.model.ReturnEntity
import com.inv.e_inventoryupdate.model.StockEntity
import com.inv.e_inventoryupdate.model.SupplierEntity


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [StockEntity::class, SupplierEntity::class, ReturnEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inventory_update_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
