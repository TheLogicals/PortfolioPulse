package com.example.upstoxportfolioscreen.data.network

import com.example.upstoxportfolioscreen.data.model.Holdings
import retrofit2.Response
import retrofit2.http.GET

interface HoldingsApiService {
    @GET(".")
    suspend fun getHoldings(): Response<Holdings>
}