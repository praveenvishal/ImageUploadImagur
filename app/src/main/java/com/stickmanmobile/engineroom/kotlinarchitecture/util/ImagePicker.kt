package com.stickmanmobile.engineroom.kotlinarchitecture.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ImagePicker {
    companion object {
        fun openGallery(context: Activity, requestCode: Int) {
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            context.startActivityForResult(intent, requestCode)
        }

        fun openCamera(context: Activity, requestCode: Int) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(context.packageManager) != null) {
                var photoFile: File? = null
                try {
                    photoFile = createImageFile(context)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }

                if (photoFile != null) {
                    val photoURI =
                            if (Build.VERSION.SDK_INT >= 24) {
                                FileProvider.getUriForFile(context, "com.stickmanmobile.engineroom.kotlinarchitecture.android.fileprovider", photoFile)
                            } else {
                                Uri.fromFile(photoFile)
                            }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    } else {
                        val resInfoList = context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                        for (resolveInfo in resInfoList) {
                            val packageName = resolveInfo.activityInfo.packageName
                            context.grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile.absolutePath)
                    context.startActivityForResult(intent, requestCode)
                }
            }
        }


        @Throws(IOException::class)
        fun createImageFile(context: Activity): File {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            )

            return image
        }





    }

}