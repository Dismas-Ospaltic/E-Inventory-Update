package com.inv.e_inventoryupdate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inv.e_inventoryupdate.model.StockEntity
import com.inv.e_inventoryupdate.repository.StockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StockViewModel(private val stockRepository: StockRepository) : ViewModel() {

    private val _stockList = MutableStateFlow<List<StockEntity>>(emptyList())
    val stockList: StateFlow<List<StockEntity>> = _stockList

    private val _stockCount = MutableStateFlow(0)
    val stockCount: StateFlow<Int> = _stockCount

    init {
        getAllStocks()
        refreshStockCount()
    }

    // 🔹 Observe all stock updates
    private fun getAllStocks() {
        viewModelScope.launch {
            stockRepository.getAllStockUpdates().collectLatest { stocks ->
                _stockList.value = stocks
            }
        }
    }

    // 🔹 Add new stock
    fun insertStock(stock: StockEntity) {
        viewModelScope.launch {
            stockRepository.insertStock(stock)
            refreshStockCount()
        }
    }

    // 🔹 Update stock details
    fun updateStock(stock: StockEntity) {
        viewModelScope.launch {
            stockRepository.updateStock(stock)
        }
    }

    // 🔹 Update status
    fun updateStockStatus(stockId: String, newStatus: String) {
        viewModelScope.launch {
            stockRepository.updateStockStatusById(newStatus, stockId)
        }
    }

    // 🔹 Update quantity
    fun updateStockQuantity(stockId: String, newQty: Float) {
        viewModelScope.launch {
            stockRepository.updateStockQtyById(newQty, stockId)
        }
    }

    // 🔹 Delete stock
    fun deleteStock(stockId: String) {
        viewModelScope.launch {
            stockRepository.deleteStockById(stockId)
            refreshStockCount()
        }
    }

    // 🔹 Refresh total count
    fun refreshStockCount() {
        viewModelScope.launch {
            _stockCount.value = stockRepository.getAllStockUpdateCount()
        }
    }
}
