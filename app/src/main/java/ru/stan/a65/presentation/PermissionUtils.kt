package ru.stan.a65.presentation

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtils(
    private val application: Application
) {
    private lateinit  var mainActivity :MainActivity

    private lateinit var launcher: ActivityResultLauncher<Array<String>>

    fun checkPermissions() {
        val isAllGranted = REQUEST_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                mainActivity, it
            ) == PackageManager.PERMISSION_GRANTED
        }
        if (!isAllGranted) {
            launcher.launch(REQUEST_PERMISSIONS)
        }
    }

    fun isPostNotNotificationsGranted(): Boolean? {
        return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                mainActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            null
        }
    }
    fun initMainActivityContext(mainActivity: MainActivity){
        this.mainActivity = mainActivity
        this.launcher = mainActivity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { map ->
            val msq = if (map.values.all { it }) {
                " Permission is granted"
            } else "Permission is not granted"
            Toast.makeText(mainActivity, msq, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private var INSTANCE: PermissionUtils? = null
        private var LOCK = Any()

        private var REQUEST_PERMISSIONS: Array<String> = buildList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                add(Manifest.permission.FOREGROUND_SERVICE)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }.toTypedArray()

        fun getInstance(application: Application): PermissionUtils {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                INSTANCE = PermissionUtils(application)
                return PermissionUtils(application)
            }
        }
    }
}