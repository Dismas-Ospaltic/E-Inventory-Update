package com.inv.e_inventoryupdate.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inv.e_inventoryupdate.model.SupplierEntity
import com.inv.e_inventoryupdate.repository.SupplierRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SupplierViewModel(private val supplierRepository: SupplierRepository) : ViewModel() {

    private val _suppliers = MutableStateFlow<List<SupplierEntity>>(emptyList())
    val suppliers: StateFlow<List<SupplierEntity>> = _suppliers

    private val _activeCount = MutableStateFlow(0)
    val activeCount: StateFlow<Int> = _activeCount

    private val _dormantCount = MutableStateFlow(0)
    val dormantCount: StateFlow<Int> = _dormantCount

    init {
        getAllSuppliers()
        refreshCounts()
    }

    private fun getAllSuppliers() {
        viewModelScope.launch {
            supplierRepository.getAllSuppliers().collectLatest { list ->
                _suppliers.value = list
            }
        }
    }

    fun insertSupplier(supplier: SupplierEntity) {
        viewModelScope.launch { supplierRepository.insertSupplier(supplier) }
    }

    fun updateSupplier(supplier: SupplierEntity) {
        viewModelScope.launch { supplierRepository.updateSupplier(supplier) }
    }

    fun deleteSupplier(supplierId: String) {
        viewModelScope.launch { supplierRepository.deleteSupplierById(supplierId) }
    }

    fun updateSupplierStatus(supplierId: String, newStatus: String) {
        viewModelScope.launch { supplierRepository.updateSupplierStatusById(newStatus, supplierId) }
    }

    fun refreshCounts() {
        viewModelScope.launch {
            _activeCount.value = supplierRepository.getActiveSupplierCount()
            _dormantCount.value = supplierRepository.getDormantSupplierCount()
        }
    }
}
