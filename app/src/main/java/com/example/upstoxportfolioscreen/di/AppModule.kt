package com.example.upstoxportfolioscreen.di

import com.example.upstoxportfolioscreen.data.HoldingsRepository
import com.example.upstoxportfolioscreen.data.network.HoldingsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): HoldingsApiService {
        return Retrofit.Builder()
            .baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HoldingsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHoldingsRepository(apiService: HoldingsApiService): HoldingsRepository {
        return HoldingsRepository(apiService)
    }
}