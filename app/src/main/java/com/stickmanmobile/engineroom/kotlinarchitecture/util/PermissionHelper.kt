package com.stickmanmobile.engineroom.kotlinarchitecture.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHelper {

    companion object {
        val REQUEST_GALLERY_CAMERA = 9002
        fun requestCameraPermission(context: AppCompatActivity): Unit {
            if (isCameraOrExternalStoragePermissionNotGranted(context)) {
                ActivityCompat.requestPermissions(
                        context,
                        arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_GALLERY_CAMERA)

            }

        }

        fun isCameraOrExternalStoragePermissionNotGranted(context: Context): Boolean {
            var isNotCameraGranted = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            var isExternalStorageNotGranted = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            return isNotCameraGranted || isExternalStorageNotGranted;

        }
    }
}