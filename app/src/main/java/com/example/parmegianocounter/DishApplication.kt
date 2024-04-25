package com.example.parmegianocounter

import android.app.Application
import com.example.parmegianocounter.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DishApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DishApplication)
            modules(appModule)
        }
    }
}