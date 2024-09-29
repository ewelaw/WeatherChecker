package com.ewela.weatherchecker

import android.app.Application
import com.ewela.feature.searchlocation.di.searchLocationDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherCheckerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@WeatherCheckerApplication)
            modules(searchLocationDiModule)
        }
    }
}