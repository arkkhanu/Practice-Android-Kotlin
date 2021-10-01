package com.ark.allinone.viewmodel

import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ark.allinone.Maps.OfflineLatLng
import com.google.android.gms.maps.model.LatLng
import okhttp3.internal.wait

class Map_ViewModel(private val offlineLatLng: OfflineLatLng) : ViewModel() {

    private val mutablelatLng: MutableLiveData<LatLng> = MutableLiveData()
    val latLng: LiveData<LatLng>
        get() = mutablelatLng

    @RequiresPermission(allOf = [android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])
    fun requestforcurrentLocation() {
        try {
//            offlineLatLng.requestLatLng().wait()
//            mutablelatLng.value = offlineLatLng.requestLatLng()
//            mutablelatLng.value = offlineLatLng.requestLatLng1()
            offlineLatLng.requestLatLng1()?.addOnSuccessListener {
                mutablelatLng.value = LatLng(it.latitude, it.longitude)
            }
                ?.addOnFailureListener {
                    mutablelatLng.value = LatLng(0.0, 0.0)
                }
        } catch (e: Exception) {
            mutablelatLng.value = (offlineLatLng.latLng)
        }

    }


}