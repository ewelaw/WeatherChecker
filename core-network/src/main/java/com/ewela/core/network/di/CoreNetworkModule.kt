package com.ewela.core.network.di

import com.ewela.core.network.providers.AuthInterceptor
import com.ewela.core.network.providers.OkHttpProvider
import com.ewela.core.network.providers.RetrofitProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreNetworkModule = module {
    singleOf(::AuthInterceptor)
    singleOf(::OkHttpProvider)
    singleOf(::RetrofitProvider)
}