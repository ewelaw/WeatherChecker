package com.ewela.feature.searchlocation.di

import com.ewela.feature.searchlocation.ui.SearchLocationViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val searchLocationDiModule = module {
    viewModelOf<SearchLocationViewModel>(::SearchLocationViewModel)
}