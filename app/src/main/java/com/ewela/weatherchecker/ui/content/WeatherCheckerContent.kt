package com.ewela.weatherchecker.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ewela.weatherchecker.ui.navigation.SearchLocation
import com.ewela.weatherchecker.ui.theme.WeatherCheckerTheme

@Composable
fun WeatherCheckerContent() {
    WeatherCheckerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                WeatherCheckerNavHost()
            }
        }
    }
}

@Composable
private fun WeatherCheckerNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SearchLocation
    ) {
        composable<SearchLocation> {
        }
    }
}