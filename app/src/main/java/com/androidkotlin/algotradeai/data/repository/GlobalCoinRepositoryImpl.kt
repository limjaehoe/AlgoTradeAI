package com.androidkotlin.algotradeai.data.repository

import android.util.Log
import com.androidkotlin.algotradeai.data.remote.api.CoinGeckoApiService
import com.androidkotlin.algotradeai.domain.model.ChartData
import com.androidkotlin.algotradeai.domain.model.ChartEntry
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.model.CoinDetail
import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import java.util.Date
import javax.inject.Inject

class GlobalCoinRepositoryImpl @Inject constructor(
    private val coinGeckoApiService: CoinGeckoApiService
) : GlobalCoinRepository {
    override suspend fun getGlobalCoinMarkets(): List<Coin> {
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


    // 새로운 메서드 구현
    override suspend fun getCoinDetail(coinId: String): CoinDetail? {
        return try {
            val response = coinGeckoApiService.getCoinDetail(coinId)

            if (response.isSuccessful) {
                val detailDto = response.body()

                if (detailDto != null) {
                    Log.d("CoinRepositoryImpl", "Received detail for ${detailDto.name}")

                    // DTO를 도메인 모델로 변환
                    CoinDetail(
                        id = detailDto.id,
                        symbol = detailDto.symbol,
                        name = detailDto.name,
                        description = detailDto.description["en"] ?: "",
                        imageUrl = detailDto.images.large,
                        currentPrice = detailDto.marketData.currentPrice["usd"] ?: 0.0,
                        marketCap = detailDto.marketData.marketCap["usd"] ?: 0.0,
                        volume24h = detailDto.marketData.totalVolume["usd"] ?: 0.0,
                        high24h = detailDto.marketData.high24h["usd"] ?: 0.0,
                        low24h = detailDto.marketData.low24h["usd"] ?: 0.0,
                        priceChangePercentage24h = detailDto.marketData.priceChangePercentage24h,
                        marketCapChangePercentage24h = detailDto.marketData.marketCapChangePercentage24h,
                        website = detailDto.links.homepage.firstOrNull() ?: "",
                        twitter = detailDto.links.twitterScreenName,
                        reddit = detailDto.links.subredditUrl,
                        isKoreanExchange = false,
                        exchange = "CoinGecko"
                    )
                } else {
                    Log.e("CoinRepositoryImpl", "Null response body")
                    null
                }
            } else {
                Log.e("CoinRepositoryImpl", "API Error: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("CoinRepositoryImpl", "Error fetching coin detail", e)
            null
        }
    }

    override suspend fun getCoinMarketChart(coinId: String, days: Int): ChartData? {
        return try {
            val response = coinGeckoApiService.getCoinMarketChart(coinId, days = days)

            if (response.isSuccessful) {
                val chartDto = response.body()

                if (chartDto != null) {
                    Log.d("CoinRepositoryImpl", "Received chart data with ${chartDto.prices.size} entries")

                    // 가격 데이터를 도메인 모델로 변환
                    val priceEntries = chartDto.prices.map { entry ->
                        ChartEntry(
                            timestamp = Date(entry[0].toLong()),
                            value = entry[1]
                        )
                    }

                    // 거래량 데이터를 도메인 모델로 변환
                    val volumeEntries = chartDto.total_volumes.map { entry ->
                        ChartEntry(
                            timestamp = Date(entry[0].toLong()),
                            value = entry[1]
                        )
                    }

                    ChartData(
                        priceEntries = priceEntries,
                        volumeEntries = volumeEntries
                    )
                } else {
                    Log.e("CoinRepositoryImpl", "Null chart data response body")
                    null
                }
            } else {
                Log.e("CoinRepositoryImpl", "API Error: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("CoinRepositoryImpl", "Error fetching market chart", e)
            null
        }
    }
}