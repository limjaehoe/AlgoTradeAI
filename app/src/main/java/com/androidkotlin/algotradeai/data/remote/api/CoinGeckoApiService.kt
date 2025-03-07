package com.androidkotlin.algotradeai.data.remote.api

import com.androidkotlin.algotradeai.data.remote.dto.CoinGechoResponse
import com.androidkotlin.algotradeai.data.remote.dto.CoinGeckoDetailResponse
import com.androidkotlin.algotradeai.data.remote.dto.CoinGeckoMarketChartResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApiService {
    @GET("coins/markets")
    suspend fun getCoinMarkets(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): Response<List<CoinGechoResponse>>

    // 코인 상세 정보 요청 메서드 추가
    @GET("coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") coinId: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false
    ): Response<CoinGeckoDetailResponse>

    // 코인 차트 데이터 요청 메서드 추가
    @GET("coins/{id}/market_chart")
    suspend fun getCoinMarketChart(
        @Path("id") coinId: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: Int = 7,
        @Query("interval") interval: String = "daily"
    ): Response<CoinGeckoMarketChartResponse>
}