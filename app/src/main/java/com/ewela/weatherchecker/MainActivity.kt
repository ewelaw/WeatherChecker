package com.ewela.weatherchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ewela.feature.searchlocation.di.searchLocationDiModule
import com.ewela.weatherchecker.ui.content.WeatherCheckerContent
import com.ewela.weatherchecker.ui.theme.WeatherCheckerTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencyInjection()
        enableEdgeToEdge()
        setContent {
            WeatherCheckerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        WeatherCheckerContent()
                    }
                }
            }
        }
    }

    private fun initDependencyInjection() {
        startKoin {
            modules(searchLocationDiModule)
        }
    }
}