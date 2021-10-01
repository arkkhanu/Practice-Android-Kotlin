package com.ark.allinone.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

class PermissionsHandle() {



    fun hasPermission(context: Context, vararg permissions: String): Boolean {
        if (permissions.isNotEmpty()) {
            for (permission in permissions) {
                Log.d("ARK", "Has Permission Fun")
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    fun requestPermissions(activity: Activity, permissionCode: Int, vararg permissions: String) {
        if (permissions.isNotEmpty()) {
            for (i in permissions.indices) {
                Log.d("ARK", "RR Permission")
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                    Log.d("ARK", "If RR Permission")
                    activity.requestPermissions(arrayOf(permissions[i]), permissionCode)
                }
            }
            Log.d("ARK", "Outside RR Permission")
            activity.requestPermissions(permissions, permissionCode)
        }
    }

}

