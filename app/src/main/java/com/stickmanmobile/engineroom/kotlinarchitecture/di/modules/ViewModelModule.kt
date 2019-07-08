package com.stickmanmobile.engineroom.kotlinarchitecture.di.modules

import com.stickmanmobile.engineroom.kotlinarchitecture.ApplicationController
import com.stickmanmobile.engineroom.kotlinarchitecture.viewmodel.UploadImageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UploadImageViewModel(androidApplication() as ApplicationController,get()) }
}