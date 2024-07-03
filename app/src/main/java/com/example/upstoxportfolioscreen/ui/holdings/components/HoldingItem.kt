package com.example.upstoxportfolioscreen.ui.holdings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.upstoxportfolioscreen.data.model.Holding
import java.text.DecimalFormat

@Composable
fun HoldingItem(holding: Holding) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val profitLoss = DecimalFormat("#.##").format((holding.close - holding.ltp) * holding.quantity)
        Column(
            modifier = Modifier
                .padding(12.dp)
                .height(65.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = holding.symbol, color = Color.Black, fontSize = 14.sp)
                HoldingItemDataView(text = "LTP: ", value = "₹${holding.ltp}")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HoldingItemDataView(text = "NET QTY: ", value = "${holding.quantity}")
                HoldingItemDataView(text = "P&L: : ", value = "₹$profitLoss")
            }
        }
    }
}

@Composable
fun HoldingItemDataView(text: String, value: String) {
    Row(
        Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 10.sp, color = Color.DarkGray)
        Text(text = value)
    }
}