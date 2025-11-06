package com.inv.e_inventoryupdate


import android.app.Application
import com.inv.e_inventoryupdate.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin Dependency Injection
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}