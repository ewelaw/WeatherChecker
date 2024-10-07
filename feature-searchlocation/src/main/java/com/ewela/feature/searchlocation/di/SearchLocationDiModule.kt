package com.ewela.feature.searchlocation.di

import android.app.Application
import com.ewela.feature.searchlocation.BuildConfig
import com.ewela.feature.searchlocation.domain.usecase.GetLocationDetailsUseCase
import com.ewela.feature.searchlocation.domain.usecase.GetLocationSuggestionUseCase
import com.ewela.feature.searchlocation.domain.usecase.IsSearchQueryValidUseCase
import com.ewela.feature.searchlocation.ui.SearchLocationViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun providePlacesClient(application: Application): PlacesClient {
    Places.initialize(
        application.applicationContext,
        BuildConfig.PLACES_API_KEY
    )
    return Places.createClient(application.applicationContext)
}

val searchLocationDiModule = module {
    factoryOf(::IsSearchQueryValidUseCase)
    factoryOf(::GetLocationSuggestionUseCase)
    factoryOf(::GetLocationDetailsUseCase)
    single<PlacesClient> { providePlacesClient(androidApplication()) }
    viewModelOf(::SearchLocationViewModel)
}