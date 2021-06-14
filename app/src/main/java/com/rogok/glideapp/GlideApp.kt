package com.rogok.glideapp

import android.app.Application
import com.rogok.glideapp.di.repositoryModule
import com.rogok.glideapp.di.retrofitModule
import com.rogok.glideapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GlideApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger(Level.NONE)
            //inject Android context
            androidContext(this@GlideApp)
            // use modules
            modules(
                listOf(
                    retrofitModule,
                    repositoryModule,
                    viewModelModule

                )
            )
        }
    }
}