package com.ewela.core.network.providers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://api.openweathermap.org"

 class RetrofitProvider(private val okHttpProvider: OkHttpProvider) {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpProvider.okHttpClient)
        .build()

}