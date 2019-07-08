package com.stickmanmobile.engineroom.kotlinarchitecture.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.stickmanmobile.engineroom.kotlinarchitecture.ApplicationController
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.api.repository.IUploadImageRepository
import com.stickmanmobile.engineroom.kotlinarchitecture.api.repository.UploadImageRepositoryImp
import com.stickmanmobile.engineroom.kotlinarchitecture.api.response.UploadImageResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.constants.ApiConstant
import com.stickmanmobile.engineroom.kotlinarchitecture.worker.UploadImageWorker
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.File

class UploadImageViewModel constructor(app: ApplicationController,val iUploadImageRepository: IUploadImageRepository) : AndroidViewModel(app), KoinComponent {
    private val workManager = WorkManager.getInstance()
    private var uploadImageResponseLiveData=MutableLiveData<ApiResponse<UploadImageResponse>>()


    /*internal fun uploadImage(imagePath: String) {
        val builder = Data.Builder()
        imagePath?.let {
            builder.putString(UploadImageWorker.KEY_IMAGE_PATH, imagePath.toString())
        }
        val data= builder.build()
        val uploadWorkBuilder= OneTimeWorkRequestBuilder<UploadImageWorker>().setInputData(data)
        workManager.enqueue(uploadWorkBuilder.build())
    }*/

    fun getUploadImage():MutableLiveData<ApiResponse<UploadImageResponse>>{
         return uploadImageResponseLiveData

    }

    fun uploadImage(imagePath:String){
        iUploadImageRepository.uploadImage(imagePath,uploadImageResponseLiveData)

    }

}