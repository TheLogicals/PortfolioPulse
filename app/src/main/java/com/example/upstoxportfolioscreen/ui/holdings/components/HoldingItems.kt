package com.example.upstoxportfolioscreen.ui.holdings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.upstoxportfolioscreen.data.model.Holding
import com.example.upstoxportfolioscreen.ui.utils.Resource

@Composable
fun HoldingItems(
    holdingsEvent: Resource<List<Holding>>,
    paddingValues: PaddingValues
) {
    Surface(color = Color(android.graphics.Color.parseColor("#FDFDFB"))) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (holdingsEvent) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(paddingValues)
                            .size(60.dp)
                    )
                }

                is Resource.Success -> {
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        itemsIndexed(items = holdingsEvent.data) { index, holding ->
                            HoldingItem(holding)
                            if (index != holdingsEvent.data.size - 1) {
                                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                            }
                        }
                    }
                }

                is Resource.Failure -> {
                    val message = holdingsEvent.message
                    Text(
                        text = "Failure: $message", modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    )
                }

                is Resource.Empty -> {
                    Text(
                        text = "No holdings found", modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}