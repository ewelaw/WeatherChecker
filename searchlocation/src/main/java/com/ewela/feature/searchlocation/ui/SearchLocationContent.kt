package com.ewela.feature.searchlocation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ewela.feature.searchlocation.R

@Composable
fun SearchLocationContent(
    searchQuery: String,
    shouldShowLocationQueryError: Boolean,
    onQueryChanged: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            value = searchQuery,
            onValueChange = onQueryChanged,
            singleLine = true,
            label = {
                Text(stringResource(R.string.search_location_label))
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable(enabled = true, onClick = onSearchClick),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search icon"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable(enabled = true, onClick = onClearClick),
                        painter = painterResource(
                            id = R.drawable.ic_clear
                        ),
                        contentDescription = "Clear icon"
                    )
                }
            },
            supportingText = {
                if (shouldShowLocationQueryError) {
                    Text(
                        text = stringResource(id = R.string.search_location_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
        )
    }
}

@Preview
@Composable
private fun SearchLocationContentPreview() {
    SearchLocationContent(
        shouldShowLocationQueryError = false,
        searchQuery = "",
        onSearchClick = {},
        onQueryChanged = {},
        onClearClick = {}
    )
}