package com.ewela.feature.searchlocation.ui.locationsuggestions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ewela.feature.searchlocation.R

@Composable
fun LocationSuggestionsErrorContent(
    onRefreshSuggestionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.search_location_suggestion_error),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        FilledIconButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRefreshSuggestionClick,
            content = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_refresh),
                        contentDescription = "Refresh icon"
                    )
                    Text(
                        text = "Refresh",
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            })
    }
}

@Preview
@Composable
private fun LocationSuggestionsErrorContent() {
    LocationSuggestionsErrorContent({})
}