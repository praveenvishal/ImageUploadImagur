package com.stickmanmobile.engineroom.kotlinarchitecture.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiServices
import org.koin.core.KoinComponent
import org.koin.core.inject

class UploadImageWorker(context: Context, params: WorkerParameters) : Worker(context, params), KoinComponent {

    val TAG by lazy { UploadImageWorker::class.java.simpleName }
    val apiServices: ApiServices by inject()

    companion object {
        const val KEY_IMAGE_PATH = "KEY_IMAGE_PATH"

    }

    override fun doWork(): Result {
//        val filePath = inputData.getString(KEY_IMAGE_PATH)
//        val imageFile = File(filePath)
//        val requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
//        val part = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
//        val requestBodyDescription = RequestBody.create(MediaType.parse("text/plain"), "description")
//        val responseBody = apiServices.uploadImage(part, requestBodyDescription)
//        responseBody.execute()
        return Result.success()
    }



}