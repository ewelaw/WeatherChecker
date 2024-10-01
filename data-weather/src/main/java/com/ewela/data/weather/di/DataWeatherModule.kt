package com.ewela.data.weather.di

import com.ewela.data.weather.providers.WeatherApiProvider
import com.ewela.data.weather.repository.WeatherRepository
import com.ewela.data.weather.repository.WeatherRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataWeatherModule = module {
    singleOf(::WeatherApiProvider)
    single { WeatherRepositoryImpl(get()) as WeatherRepository }
}