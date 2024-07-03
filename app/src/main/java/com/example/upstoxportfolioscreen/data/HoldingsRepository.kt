package com.example.upstoxportfolioscreen.data

import com.example.upstoxportfolioscreen.data.model.Holding
import com.example.upstoxportfolioscreen.data.network.HoldingsApiService
import com.example.upstoxportfolioscreen.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HoldingsRepository @Inject constructor(private val apiService: HoldingsApiService) {

    fun getHoldings(): Flow<Resource<List<Holding>>> = flow {
        emit(Resource.Loading)
        try {
            val holdingsResponse = apiService.getHoldings()
            if (holdingsResponse.isSuccessful) {
                val holdings = holdingsResponse.body()?.data?.userHolding
                if (holdings.isNullOrEmpty()) {
                    emit(Resource.Empty)
                } else {
                    emit(Resource.Success(holdings))
                }
            } else {
                emit(Resource.Empty)
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e.message))
        }
    }
}

