package com.ewela.core.network.providers

import okhttp3.OkHttpClient

class OkHttpProvider(private val authInterceptor: AuthInterceptor) {

    val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addNetworkInterceptor(authInterceptor).build()
}