package com.ewela.core.network.providers

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter("appid", "b44409514a59b60119481ed9461aae61")
            .addQueryParameter("lang", "pl")
            .addQueryParameter("units", "metric")
            .build()
        val requestBuilder = chain.request().newBuilder()
            .url(url)
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(requestBuilder)
    }
}