package com.ark.allinone.Maps

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import java.util.jar.Manifest


class OfflineLatLng(activity: Activity) {

    var latLng: LatLng = LatLng(0.0, 0.0)
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    init {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    }

    @RequiresPermission(allOf = [android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])
    fun requestLatLng(): LatLng {
        Log.d("ARK LATLNG function", " in function")
        var _latLng: LatLng = LatLng(0.0, 0.0)
        fusedLocationProviderClient?.let {
            val lastLocation: Task<Location> = it.lastLocation
            lastLocation.addOnSuccessListener { loc ->
                _latLng = LatLng(loc.latitude, loc.longitude)
                Log.d("ARK LATLNG Success", "${loc.longitude} ${loc.latitude}")
            }
            lastLocation.addOnFailureListener {
                _latLng = LatLng(0.0, 0.0)
                Log.d("ARK LATLNG Failed", "0 , 0")
            }
        }
        latLng = _latLng
        return _latLng
    }

    @RequiresPermission(allOf = [android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])
    fun requestLatLng1(): Task<Location>? {
        return fusedLocationProviderClient?.lastLocation
    }


}