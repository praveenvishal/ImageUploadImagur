package com.stickmanmobile.engineroom.kotlinarchitecture.ui.uploadimage

import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.stickmanmobile.engineroom.kotlinarchitecture.R
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.util.DeviceHelper
import com.stickmanmobile.engineroom.kotlinarchitecture.util.ImagePicker
import com.stickmanmobile.engineroom.kotlinarchitecture.util.ImagePicker.Companion.openCamera
import com.stickmanmobile.engineroom.kotlinarchitecture.util.PermissionHelper
import com.stickmanmobile.engineroom.kotlinarchitecture.util.PermissionHelper.Companion.REQUEST_GALLERY_CAMERA
import com.stickmanmobile.engineroom.kotlinarchitecture.viewmodel.UploadImageViewModel
import kotlinx.android.synthetic.main.fragment_upload.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UploadImageActivity : AppCompatActivity(), View.OnClickListener {

    val REQUEST_CODE_OPEN_CAMERA: Int = 9001
    val REQUEST_CODE_OPEN_GALLERY: Int = 9002
    val uploadViewModel: UploadImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_upload)
        openCameraButton.setOnClickListener(this)
        openGalleryButton.setOnClickListener(this)
        uploadViewModel.getUploadImage().observe(this@UploadImageActivity, Observer {
            if (it != null) {
                showHideProgress(it.status == ApiResponse.Status.LOADING)
                Toast.makeText(this@UploadImageActivity,"Uploaded",Toast.LENGTH_LONG).show()

            }

        })
    }

    fun showHideProgress(show: Boolean) {
        if(show)
            progressBar.visibility=View.VISIBLE
        else
            progressBar.visibility=View.GONE

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_OPEN_CAMERA -> {
                    val extras = data?.getExtras()
                    val imageBitmap = extras?.get("data") as Bitmap
                }
                REQUEST_CODE_OPEN_GALLERY -> {
                    showHideProgress(true)
                    Handler().postDelayed(Runnable {
                        handleImage(data)


                    }, 1500)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_GALLERY_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openCamera(this@UploadImageActivity, REQUEST_CODE_OPEN_CAMERA)
            }
        }
    }


    private fun handleImage(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(this, uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri.authority) {
                val id = docId.split(":")[1]
                val selsetion = MediaStore.Images.Media._ID + "=" + id
                imagePath = imagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selsetion)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(docId))
                imagePath = imagePath(contentUri, null)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            imagePath = imagePath(uri, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            imagePath = uri.path
        }
        imagePath?.let { uploadViewModel.uploadImage(imagePath) }


    }


    private fun imagePath(uri: Uri?, selection: String?): String {
        var path: String? = null
        val cursor = contentResolver.query(uri, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path!!
    }

    override fun onClick(v: View?) {
        when (v) {
            openCameraButton -> {
                if (DeviceHelper.isOrAboveMarshmallow()) {
                    PermissionHelper.requestCameraPermission(this@UploadImageActivity)
                    ImagePicker.openCamera(this@UploadImageActivity, REQUEST_CODE_OPEN_CAMERA);
                } else
                    PermissionHelper.requestCameraPermission(this@UploadImageActivity)
            }
            openGalleryButton -> if (DeviceHelper.isOrAboveMarshmallow()) {
                PermissionHelper.requestCameraPermission(this@UploadImageActivity)
                ImagePicker.openGallery(this@UploadImageActivity, REQUEST_CODE_OPEN_GALLERY);
            } else
                PermissionHelper.requestCameraPermission(this@UploadImageActivity)
        }
    }

}





