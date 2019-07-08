package com.stickmanmobile.engineroom.kotlinarchitecture.api.repository

import androidx.lifecycle.MutableLiveData
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.api.response.UploadImageResponse

interface IUploadImageRepository{
    fun uploadImage(imagePath:String,apiResponse: MutableLiveData<ApiResponse<UploadImageResponse>>)

}