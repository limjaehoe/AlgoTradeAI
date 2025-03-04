package com.androidkotlin.algotradeai.data.repository

import android.util.Log
import com.androidkotlin.algotradeai.data.remote.api.CoinGeckoApiService
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinGeckoApiService: CoinGeckoApiService
) : CoinRepository {
    override suspend fun getCoinMarkets(): List<Coin> {
        return try {
            val response = coinGeckoApiService.getCoinMarkets()

            if (response.isSuccessful) {
                val coinDtoList = response.body() ?: emptyList()

                Log.d("CoinRepositoryImpl", "Received ${coinDtoList.size} coins")

                coinDtoList.map { coinDto ->
                    Log.d("CoinRepositoryImpl", "Converting coin: ${coinDto.name}")
                    Coin(
                        id = coinDto.id,
                        symbol = coinDto.symbol,
                        name = coinDto.name,
                        currentPrice = coinDto.current_price,
                        priceChangePercentage = coinDto.price_change_percentage_24h
                    )
                }
            } else {
                Log.e("CoinRepositoryImpl", "API Error: ${response.code()} - ${response.message()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("CoinRepositoryImpl", "Error fetching coin markets", e)
            emptyList()
        }
    }
}