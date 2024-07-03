package com.example.upstoxportfolioscreen.ui.holdings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.upstoxportfolioscreen.data.model.Holding
import java.text.DecimalFormat

@Composable
fun BottomBar(holdingsData: List<Holding>) {
    var bottomSheetExpanded by remember { mutableStateOf(false) }
    var currentValue = 0.0
    var totalInvestment = 0.0
    var profitLoss = 0.0
    holdingsData.forEach {
        currentValue += it.close * it.quantity
        totalInvestment += it.ltp * it.quantity
        profitLoss += (it.close - it.ltp) * it.quantity
    }
    val profitPercentage = (profitLoss * 100) / totalInvestment
    val bottomSheetItems = listOf(
        BottomSheetItemData("Current Value*", currentValue),
        BottomSheetItemData("Total Investment*", currentValue),
        BottomSheetItemData("Today's Profit & Loss*", profitLoss),
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
        color = Color(android.graphics.Color.parseColor("#F2F2F2"))
    ) {
        Column {
            BottomSheet(
                data = bottomSheetItems,
                isExpanded = bottomSheetExpanded,
                onExpand = { bottomSheetExpanded = !bottomSheetExpanded }
            )
            BottomView(
                dailyProfit = profitLoss,
                profitPercentage = profitPercentage,
                onClick = { bottomSheetExpanded = !bottomSheetExpanded },
                upIcon = if (bottomSheetExpanded) "^" else "^"
            )
        }
    }
}


@Composable
fun BottomView(
    dailyProfit: Double,
    profitPercentage: Double,
    onClick: () -> Unit,
    upIcon: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        color = Color(android.graphics.Color.parseColor("#F2F2F2")),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
    ) {
        val decimalFormat = DecimalFormat("#.##")
        val formatedDailyProfit = decimalFormat.format(dailyProfit)
        val formatedDailyProfitPercent = decimalFormat.format(profitPercentage)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Profit & Loss* $upIcon",
                fontSize = 16.sp
            )
            Text(
                text = "₹$formatedDailyProfit($formatedDailyProfitPercent%)",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun BottomSheet(
    data: List<BottomSheetItemData>,
    isExpanded: Boolean, onExpand: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.DarkGray)
            .clickable { onExpand() },
        color = Color(android.graphics.Color.parseColor("#F2F2F2"))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isExpanded) {
                data.forEach {
                    BottomSheetItem(text = it.text, value = it.value)
                }
            }
        }
    }
}

@Composable
fun BottomSheetItem(text: String, value: Double) {
    val formatedValue = DecimalFormat("#.##").format(value)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = text)
        Text(text = "₹ $formatedValue")
    }
}

data class BottomSheetItemData(val text: String, val value: Double)