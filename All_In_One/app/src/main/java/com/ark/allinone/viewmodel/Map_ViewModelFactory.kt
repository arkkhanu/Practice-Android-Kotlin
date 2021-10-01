package com.ark.allinone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ark.allinone.Maps.OfflineLatLng

class Map_ViewModelFactory(val offlineLatLng: OfflineLatLng) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Map_ViewModel(offlineLatLng) as T
    }
}