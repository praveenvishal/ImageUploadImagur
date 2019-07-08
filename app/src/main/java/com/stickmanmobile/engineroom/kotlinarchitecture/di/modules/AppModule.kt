package com.stickmanmobile.engineroom.kotlinarchitecture.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.stickmanmobile.engineroom.kotlinarchitecture.api.ApiServices
import com.stickmanmobile.engineroom.kotlinarchitecture.constants.ApiConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var appModule= module {

    single<Gson> {
        val builder = GsonBuilder().serializeNulls()
        builder.setLenient().create()
    }

    single {
        var loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        var httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.connectTimeout(ApiConstant.API_TIME_OUT, TimeUnit.MILLISECONDS)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                    .header("Authorization", "Client-ID "+ApiConstant.CLIENT_ID) // <-- this is the important line

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        var okHttpClient = httpClient.build()
        Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(get() as Gson))
                .build()
    }
    single {
        (get<Retrofit>()).create<ApiServices>(ApiServices::class.java)
    }







}