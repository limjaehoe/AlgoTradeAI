package com.androidkotlin.algotradeai.data.repository

import android.util.Log
import com.androidkotlin.algotradeai.data.remote.api.UpbitApiService
import com.androidkotlin.algotradeai.data.remote.api.BithumbApiService
import com.androidkotlin.algotradeai.data.remote.api.CoinGeckoApiService
import com.androidkotlin.algotradeai.domain.Coin


import com.androidkotlin.algotradeai.domain.MultiExchangeRepository
import javax.inject.Inject

class MultiExchangeRepositoryImpl @Inject constructor(
    private val upbitApiService: UpbitApiService,
    private val bithumbApiService: BithumbApiService
) : MultiExchangeRepository {
    override suspend fun getKoreanCoinPrices(): List<Coin> {
        return try {
            Log.d("MultiExchangeRepositoryImpl", "Fetching Korean coin prices")

            val upbitResponse = upbitApiService.getCoinTickers(listOf("KRW-BTC", "KRW-ETH"))

            if (upbitResponse.isSuccessful) {
                val upbitCoins = upbitResponse.body() ?: emptyList()

                Log.d("MultiExchangeRepositoryImpl", "Upbit coins: ${upbitCoins.size}")

                upbitCoins.map { upbitCoin ->
                    Coin(
                        id = upbitCoin.market,
                        symbol = upbitCoin.market.split("-").last(),
                        name = upbitCoin.market.split("-").last(),
                        currentPrice = upbitCoin.tradePrice,
                        priceChangePercentage = upbitCoin.changeRate * 100
                    )
                }
            } else {
                Log.e("MultiExchangeRepositoryImpl", "Upbit API Error: ${upbitResponse.code()} - ${upbitResponse.message()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("MultiExchangeRepositoryImpl", "Error fetching Korean coin prices", e)
            emptyList()
        }
    }
}