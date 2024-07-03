package com.example.upstoxportfolioscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.upstoxportfolioscreen.ui.holdings.HoldingsScreen
import com.example.upstoxportfolioscreen.ui.theme.UpstoxPortfolioScreenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpstoxPortfolioScreenTheme(darkTheme = false) {
                HoldingsScreen()
            }
        }
    }
}