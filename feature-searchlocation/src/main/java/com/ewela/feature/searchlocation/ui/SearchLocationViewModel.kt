package com.ewela.feature.searchlocation.ui

import androidx.lifecycle.ViewModel
import com.ewela.feature.searchlocation.domain.model.Location
import com.ewela.feature.searchlocation.domain.usecase.GetLocationDetailsUseCase
import com.ewela.feature.searchlocation.domain.usecase.GetLocationSuggestionUseCase
import com.ewela.feature.searchlocation.domain.usecase.IsSearchQueryValidUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class SearchLocationViewModel(
    private val isSearchQueryValidUseCase: IsSearchQueryValidUseCase,
    private val getLocationSuggestionUseCase: GetLocationSuggestionUseCase,
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase
) : ViewModel() {

    val locationQuery = MutableStateFlow("")
    val isQueryValid = MutableStateFlow(true)
    val locationSuggestions: MutableStateFlow<List<Location>> =
        MutableStateFlow(mutableListOf())
    val errorLoadingLocationSuggestions = MutableStateFlow(false)
    val isLoadingLocationDetails = MutableStateFlow(false)
    val isLoadingLocationSuggestions = MutableStateFlow(false)

    fun onQueryChanged(newValue: String) {
        locationQuery.value = newValue
        validateQuery()
    }

    fun onClearClick() {
        locationQuery.value = ""
        isQueryValid.value = true
        locationSuggestions.value = mutableListOf()
    }

    fun onLocationClick(location: Location, onSuccess: (Location) -> Unit, onFailure: () -> Unit) =
        getLocationDetails(
            location = location,
            onLocationEstablished = onSuccess,
            onFailure = onFailure
        )

    fun onRefreshSuggestionsClick() =
        getLocationSuggestion()

    private fun validateQuery() {
        isQueryValid.value = isSearchQueryValidUseCase(locationQuery.value)
        if (isQueryValid.value) getLocationSuggestion()
        else locationSuggestions.value = emptyList()
    }

    private fun getLocationSuggestion() {
        isLoadingLocationSuggestions.value = true
        getLocationSuggestionUseCase(
            query = locationQuery.value,
            onSuccess = {
                locationSuggestions.value = it
                errorLoadingLocationSuggestions.value = false
            }, onFailure = {
                errorLoadingLocationSuggestions.value = true
            })
        isLoadingLocationSuggestions.value = false
    }

    private fun getLocationDetails(
        location: Location,
        onLocationEstablished: (Location) -> Unit,
        onFailure: () -> Unit
    ) {
        isLoadingLocationDetails.value = true
        getLocationDetailsUseCase(
            location = location,
            onSuccess = onLocationEstablished,
            onFailure = onFailure
        )
        isLoadingLocationDetails.value = false
    }
}


