package com.androidkotlin.algotradeai.data.repository

import android.util.Log
import com.androidkotlin.algotradeai.data.remote.api.UpbitApiService
import com.androidkotlin.algotradeai.data.remote.api.BithumbApiService
import com.androidkotlin.algotradeai.domain.model.Coin


import com.androidkotlin.algotradeai.domain.repository.MultiExchangeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class MultiExchangeRepositoryImpl @Inject constructor(
    private val upbitApiService: UpbitApiService,
    private val bithumbApiService: BithumbApiService
) : MultiExchangeRepository {
    override suspend fun getKoreanCoinPrices(): List<Coin> = coroutineScope {
        val coinList = mutableListOf<Coin>()

        try {
            // 병렬로 여러 API 호출
            val upbitDeferred = async {
                try {
                    Log.d("MultiExchangeRepo", "Fetching Upbit prices")
                    val upbitResponse = upbitApiService.getCoinTickers(listOf("KRW-BTC", "KRW-ETH"))

                    if (upbitResponse.isSuccessful) {
                        val upbitCoins = upbitResponse.body() ?: emptyList()
                        upbitCoins.map { upbitCoin ->
                            Coin(
                                id = upbitCoin.market, // "KRW-BTC"와 같은 형식
                                symbol = upbitCoin.market.split("-").last(),
                                name = "${upbitCoin.market.split("-").last()} (업비트)",
                                currentPrice = upbitCoin.tradePrice,
                                priceChangePercentage = upbitCoin.changeRate * 100
                            )
                        }
                    } else {
                        Log.e("MultiExchangeRepo", "Upbit API Error: ${upbitResponse.code()}")
                        emptyList()
                    }
                } catch (e: Exception) {
                    Log.e("MultiExchangeRepo", "Error fetching Upbit prices", e)
                    emptyList()
                }
            }

            val bithumbDeferred = async {
                try {
                    Log.d("MultiExchangeRepo", "Fetching Bithumb prices")
                    // BTC와 ETH에 대해 개별적으로 호출
                    val bithumbCoins = listOf("BTC", "ETH").mapNotNull { symbol ->
                        try {
                            val response = bithumbApiService.getCoinTicker(symbol)
                            Coin(
                                id = "bithumb-$symbol",
                                symbol = symbol,
                                name = "$symbol (빗썸)",
                                currentPrice = response.closingPrice.toDoubleOrNull() ?: 0.0,
                                priceChangePercentage = 0.0 // 빗썸 API에서 변동률 정보가 없는 경우
                            )
                        } catch (e: Exception) {
                            Log.e("MultiExchangeRepo", "Error fetching Bithumb price for $symbol", e)
                            null
                        }
                    }
                    bithumbCoins
                } catch (e: Exception) {
                    Log.e("MultiExchangeRepo", "Error fetching Bithumb prices", e)
                    emptyList()
                }
            }

            // 결과 합치기
            val upbitCoins = upbitDeferred.await()
            val bithumbCoins = bithumbDeferred.await()

            coinList.addAll(upbitCoins)
            coinList.addAll(bithumbCoins)

            Log.d("MultiExchangeRepo", "Total Korean exchange coins: ${coinList.size}")

        } catch (e: Exception) {
            Log.e("MultiExchangeRepo", "Error in getKoreanCoinPrices", e)
        }

        coinList
    }
}