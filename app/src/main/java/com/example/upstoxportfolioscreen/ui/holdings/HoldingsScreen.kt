package com.example.upstoxportfolioscreen.ui.holdings

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.upstoxportfolioscreen.ui.holdings.components.BottomBar
import com.example.upstoxportfolioscreen.ui.holdings.components.HoldingItems
import com.example.upstoxportfolioscreen.ui.utils.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoldingsScreen(viewModel: HoldingsViewModel = viewModel(), modifier: Modifier = Modifier) {
    val holdingsEvent by viewModel.holdingsEvent.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upstox Portfolio", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(
                        android.graphics.Color.parseColor(
                            "#003264"
                        )
                    )
                )
            )
        },
        bottomBar = {
            if (holdingsEvent is Resource.Success) {
                BottomBar(holdingsData = (holdingsEvent as Resource.Success).data)
            }
        }
    ) { paddingValues ->
        HoldingItems(holdingsEvent = holdingsEvent, paddingValues = paddingValues)
    }
}
