package com.stickmanmobile.engineroom.kotlinarchitecture

import android.app.Application
import android.content.Context
import com.stickmanmobile.engineroom.kotlinarchitecture.di.modules.RepositoryModule
import com.stickmanmobile.engineroom.kotlinarchitecture.di.modules.appModule
import com.stickmanmobile.engineroom.kotlinarchitecture.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class ApplicationController : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this;
        startKoin {
            androidLogger()
            androidContext(this@ApplicationController)
            modules(getModule())
        }
    }
    private fun getModule(): List<Module> {
        return listOf(appModule, viewModelModule, RepositoryModule)
    }

}