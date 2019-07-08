package com.stickmanmobile.engineroom.kotlinarchitecture.api

import com.stickmanmobile.engineroom.kotlinarchitecture.api.response.UploadImageResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.constants.ApiConstant
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiServices {

    @Multipart
    @POST(ApiConstant.UPLOAD_IMAGE)
    fun uploadImage(@Part file: MultipartBody.Part, @Part("image") requestBody: RequestBody):Deferred<Response<UploadImageResponse>>


}