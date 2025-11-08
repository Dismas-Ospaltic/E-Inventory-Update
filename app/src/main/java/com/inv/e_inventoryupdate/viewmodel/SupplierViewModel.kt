//package com.inv.e_inventoryupdate.viewmodel
//
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.inv.e_inventoryupdate.model.SupplierEntity
//import com.inv.e_inventoryupdate.repository.SupplierRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//
//class SupplierViewModel(private val supplierRepository: SupplierRepository) : ViewModel() {
//
//    private val _suppliers = MutableStateFlow<List<SupplierEntity>>(emptyList())
//    val suppliers: StateFlow<List<SupplierEntity>> = _suppliers
//
//    private val _activeCount = MutableStateFlow(0)
//    val activeCount: StateFlow<Int> = _activeCount
//
//    private val _dormantCount = MutableStateFlow(0)
//    val dormantCount: StateFlow<Int> = _dormantCount
//
//    init {
//        getAllSuppliers()
//        refreshCounts()
//    }
//
//    private fun getAllSuppliers() {
//        viewModelScope.launch {
//            supplierRepository.getAllSuppliers().collectLatest { list ->
//                _suppliers.value = list
//            }
//        }
//    }
//
//    fun insertSupplier(supplier: SupplierEntity) {
//        viewModelScope.launch { supplierRepository.insertSupplier(supplier) }
//    }
//
//    fun updateSupplier(supplier: SupplierEntity) {
//        viewModelScope.launch { supplierRepository.updateSupplier(supplier) }
//    }
//
//    fun deleteSupplier(supplierId: String) {
//        viewModelScope.launch { supplierRepository.deleteSupplierById(supplierId) }
//    }
//
//    fun updateSupplierStatus(supplierId: String, newStatus: String) {
//        viewModelScope.launch { supplierRepository.updateSupplierStatusById(newStatus, supplierId) }
//    }
//
//    fun refreshCounts() {
//        viewModelScope.launch {
//            _activeCount.value = supplierRepository.getActiveSupplierCount()
//            _dormantCount.value = supplierRepository.getDormantSupplierCount()
//        }
//    }
//
//    private val _supplierDetails = MutableStateFlow<SupplierEntity?>(null)
//    val supplierDetails: StateFlow<SupplierEntity?> = _supplierDetails
//
//    // ✅ Function to fetch supplier details by ID
//    fun fetchSupplierById(supplierId: String) {
//        viewModelScope.launch {
//            val supplier = supplierRepository.getSupplierById(supplierId)
//            _supplierDetails.value = supplier
//        }
//    }
//}
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

    private val _supplierDetails = MutableStateFlow<SupplierEntity?>(null)
    val supplierDetails: StateFlow<SupplierEntity?> = _supplierDetails

    private val _updateResult = MutableStateFlow<Boolean?>(null)
    val updateResult: StateFlow<Boolean?> = _updateResult

    init {
        getAllSuppliers()
        refreshCounts()
    }

    // ✅ Get all suppliers
    private fun getAllSuppliers() {
        viewModelScope.launch {
            supplierRepository.getAllSuppliers().collectLatest { list ->
                _suppliers.value = list
            }
        }
    }

    // ✅ Insert new supplier
    fun insertSupplier(supplier: SupplierEntity) {
        viewModelScope.launch { supplierRepository.insertSupplier(supplier) }
    }

    // ✅ Update entire supplier entity
    fun updateSupplier(supplier: SupplierEntity) {
        viewModelScope.launch { supplierRepository.updateSupplier(supplier) }
    }

    // ✅ Delete supplier by ID
    fun deleteSupplier(supplierId: String) {
        viewModelScope.launch { supplierRepository.deleteSupplierById(supplierId) }
    }

    // ✅ Update supplier status by ID
    fun updateSupplierStatus(supplierId: String, newStatus: String) {
        viewModelScope.launch { supplierRepository.updateSupplierStatusById(newStatus, supplierId) }
    }

    // ✅ Refresh active/dormant counts
    fun refreshCounts() {
        viewModelScope.launch {
            _activeCount.value = supplierRepository.getActiveSupplierCount()
            _dormantCount.value = supplierRepository.getDormantSupplierCount()
        }
    }

    // ✅ Fetch supplier details by ID
    fun fetchSupplierById(supplierId: String) {
        viewModelScope.launch {
            val supplier = supplierRepository.getSupplierById(supplierId)
            _supplierDetails.value = supplier
        }
    }

    // ✅ Update supplier by ID (returns success/failure)
    fun updateSupplierById(
        supplierId: String,
        supplierName: String,
        supplierPhone: String,
        supplierEmail: String,
        supplierAddress: String
    ) {
        viewModelScope.launch {
            val isUpdated = supplierRepository.updateSupplierById(
                supplierName = supplierName,
                supplierPhone = supplierPhone,
                supplierEmail = supplierEmail,
                supplierAddress = supplierAddress,
                supplierId = supplierId
            )
            _updateResult.value = isUpdated
        }
    }
}
