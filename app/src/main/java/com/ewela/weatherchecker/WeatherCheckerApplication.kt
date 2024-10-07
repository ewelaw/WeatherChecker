package com.ewela.weatherchecker

import android.app.Application
import com.ewela.core.network.di.coreNetworkModule
import com.ewela.data.weather.di.dataWeatherModule
import com.ewela.feature.searchlocation.di.searchLocationDiModule
import com.ewela.feature.weatherdetails.di.weatherDetailsDiModule
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
            modules(
                searchLocationDiModule,
                weatherDetailsDiModule,
                dataWeatherModule,
                coreNetworkModule
            )
        }
    }
}