package com.ewela.feature.searchlocation.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchLocationLayout(
    viewModel: SearchLocationViewModel = koinViewModel<SearchLocationViewModel>()
) {
    val searchQuery = viewModel.locationQuery.collectAsStateWithLifecycle()
    val shouldShowLocationQueryError = viewModel.shouldShowLocationQueryError.collectAsStateWithLifecycle()
    SearchLocationContent(
        searchQuery = searchQuery.value,
        onQueryChanged = viewModel::onQueryChanged,
        onSearchClick = {},
        onClearClick = viewModel::onClearClick,
        shouldShowLocationQueryError = shouldShowLocationQueryError.value
    )
}
