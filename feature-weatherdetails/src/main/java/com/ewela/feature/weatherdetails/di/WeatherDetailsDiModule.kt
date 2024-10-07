package com.ewela.feature.weatherdetails.di

import com.ewela.feature.weatherdetails.ui.WeatherDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val weatherDetailsDiModule = module {
    viewModelOf(::WeatherDetailsViewModel)
}