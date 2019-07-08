package com.stickmanmobile.engineroom.kotlinarchitecture.api.repository

import androidx.lifecycle.MutableLiveData
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiServices
import com.stickmanmobile.engineroom.kotlinarchitecture.api.DataFetchCall
import com.stickmanmobile.engineroom.kotlinarchitecture.api.response.UploadImageResponse
import com.stickmanmobile.engineroom.kotlinarchitecture.constants.ApiConstant
import kotlinx.coroutines.Deferred
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.core.KoinComponent
import retrofit2.Response
import java.io.File

class UploadImageRepositoryImp(val apiServices: ApiServices) : IUploadImageRepository ,KoinComponent{
    override fun uploadImage(imagePath: String, apiResponse: MutableLiveData<ApiResponse<UploadImageResponse>>) {
        object : DataFetchCall<UploadImageResponse>(apiResponse) {
            override fun saveResult(result: UploadImageResponse) {
            }

            override fun shouldFetchFromDB(): Boolean {
                return false;
            }

            override fun loadFromDB(): UploadImageResponse? {
                return null
            }

            override fun createCallAsync(): Deferred<Response<UploadImageResponse>> {
                val imageFile = File(imagePath)
                val requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
                val part = MultipartBody.Part.createFormData(ApiConstant.UPLOAD_IMAGE_KEY, imageFile.name, requestBody)
                val requestBodyDescription = RequestBody.create(MediaType.parse("text/plain"), "description")
                return apiServices.uploadImage(part,requestBodyDescription)


            }


        }.execute()


    }

}