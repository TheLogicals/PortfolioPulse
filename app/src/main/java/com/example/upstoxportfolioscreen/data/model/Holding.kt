package com.example.upstoxportfolioscreen.data.model

data class Holding(
    val avgPrice: Double,
    val close: Double,
    val ltp: Double,
    val quantity: Int,
    val symbol: String
)