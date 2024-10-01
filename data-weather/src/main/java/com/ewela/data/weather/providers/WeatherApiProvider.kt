package com.ewela.data.weather.providers

import com.ewela.core.network.providers.RetrofitProvider
import com.ewela.data.weather.api.WeatherApi

internal class WeatherApiProvider(private val retrofitProvider: RetrofitProvider) {

    val weatherApi: WeatherApi by lazy { retrofitProvider.retrofit.create(WeatherApi::class.java) }
}