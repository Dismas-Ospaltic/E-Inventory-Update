package com.inv.e_inventoryupdate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inv.e_inventoryupdate.model.ReturnEntity
import com.inv.e_inventoryupdate.repository.ReturnRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ReturnViewModel(private val returnRepository: ReturnRepository) : ViewModel() {

    private val _returns = MutableStateFlow<List<ReturnEntity>>(emptyList())
    val returns: StateFlow<List<ReturnEntity>> = _returns

    private val _returnsCount = MutableStateFlow(0)
    val returnsCount: StateFlow<Int> = _returnsCount

    init {
        getAllReturns()
        refreshReturnsCount()
    }

    private fun getAllReturns() {
        viewModelScope.launch {
            returnRepository.getAllReturns().collectLatest { list ->
                _returns.value = list
            }
        }
    }

    fun insertReturn(returns: ReturnEntity) {
        viewModelScope.launch {
            returnRepository.insertStockReturn(returns)
        }
    }

    fun updateReturn(returns: ReturnEntity) {
        viewModelScope.launch {
            returnRepository.updateStockReturn(returns)
        }
    }

    fun refreshReturnsCount() {
        viewModelScope.launch {
            _returnsCount.value = returnRepository.getAllReturnsCount()
        }
    }
}
