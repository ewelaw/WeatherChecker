package com.ewela.core.network.providers

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private val appIdKey = "appId"
    private val appIdValue = "b44409514a59b60119481ed9461aae61"
    private val unitsKey = "units"
    private val unitsValue = "metric"
    private val languageKey = "lang"
    private val languageValue = "pl"

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter(appIdKey, appIdValue)
            .addQueryParameter(languageKey, languageValue)
            .addQueryParameter(unitsKey, unitsValue)
            .build()
        val requestBuilder = chain.request().newBuilder()
            .url(url)
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(requestBuilder)
    }
}