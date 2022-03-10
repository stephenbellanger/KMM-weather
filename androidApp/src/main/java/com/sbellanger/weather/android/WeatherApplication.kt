package com.sbellanger.weather.android

import android.app.Application
import com.sbellanger.weather.android.di.viewModelModule
import com.sbellanger.weather.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@WeatherApplication)
            modules(
                viewModelModule
            )
        }
    }
}
