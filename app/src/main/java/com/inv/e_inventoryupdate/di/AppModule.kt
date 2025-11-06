package com.inv.e_inventoryupdate.di



import com.inv.e_inventoryupdate.data.local_storage.AppDatabase
import com.inv.e_inventoryupdate.repository.ReturnRepository
import com.inv.e_inventoryupdate.repository.StockRepository
import com.inv.e_inventoryupdate.repository.SupplierRepository
import com.inv.e_inventoryupdate.viewmodel.ReturnViewModel
import com.inv.e_inventoryupdate.viewmodel.StockViewModel
import com.inv.e_inventoryupdate.viewmodel.SupplierViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
//  Define ViewModel injection

    single{ AppDatabase.getDatabase(get()).stockDao() }
    single { StockRepository(get()) }
    viewModel { StockViewModel(get()) }


    single{ AppDatabase.getDatabase(get()).returnDao() }
    single { ReturnRepository(get()) }
    viewModel { ReturnViewModel(get()) }


    single{ AppDatabase.getDatabase(get()).supplierDao() }
    single { SupplierRepository(get()) }
    viewModel { SupplierViewModel(get()) }


}