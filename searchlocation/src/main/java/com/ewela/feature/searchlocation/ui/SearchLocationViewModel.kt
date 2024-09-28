package com.ewela.feature.searchlocation.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SearchLocationViewModel : ViewModel() {

    private val locationQueryRegex = "^[a-zA-Z]+$".toRegex()
    val locationQuery = MutableStateFlow("")
    val shouldShowLocationQueryError = MutableStateFlow(false)

    fun onQueryChanged(newValue: String) {
        locationQuery.value = newValue
        shouldShowLocationQueryError.value = !locationQueryRegex.matches(newValue)
    }

    fun onClearClick() {
        locationQuery.value = ""
        shouldShowLocationQueryError.value = false
    }
}