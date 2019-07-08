package com.stickmanmobile.engineroom.kotlinarchitecture.di.modules

import com.stickmanmobile.engineroom.kotlinarchitecture.api.repository.IUploadImageRepository
import com.stickmanmobile.engineroom.kotlinarchitecture.api.repository.UploadImageRepositoryImp
import org.koin.dsl.module

val RepositoryModule= module {

    single<IUploadImageRepository> { UploadImageRepositoryImp(get()) }

}