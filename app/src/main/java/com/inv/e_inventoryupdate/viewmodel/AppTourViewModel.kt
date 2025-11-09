package com.inv.e_inventoryupdate.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inv.e_inventoryupdate.data.datastore.AppTourPreferencesManager
import kotlinx.coroutines.launch

class AppTourViewModel(private val preferencesManager: AppTourPreferencesManager) : ViewModel() {

    // Exposed Flow for Onboarding state
    val isOnboardingCompleted = preferencesManager.isOnboardingCompleted

    // Method to complete onboarding
    fun completeOnboarding() {
        viewModelScope.launch {
            preferencesManager.completeOnboarding()
        }
    }

    // ✅ Clear onboarding completion state
    fun clearOnboarding() {
        viewModelScope.launch {
            preferencesManager.clearOnboarding()
        }
    }
}