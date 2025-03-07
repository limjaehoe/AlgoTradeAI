package com.androidkotlin.algotradeai.data.remote.api

import com.androidkotlin.algotradeai.data.remote.dto.UpbitCandleResponse
import com.androidkotlin.algotradeai.data.remote.dto.UpbitTickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UpbitApiService {
    @GET("ticker")
    suspend fun getCoinTickers(
        @Query("markets") markets: List<String>
    ): Response<List<UpbitTickerResponse>>

    // 코인 상세 정보 요청 메서드 추가
    @GET("ticker")
    suspend fun getCoinDetail(
        @Query("markets") market: String
    ): Response<List<UpbitTickerResponse>>

    // 캔들스틱 데이터 요청 메서드 추가
    @GET("candles/minutes/{unit}")
    suspend fun getCandlesMinutes(
        @Path("unit") unit: Int, // 1, 3, 5, 15, 30, 60, 240 중 하나
        @Query("market") market: String,
        @Query("count") count: Int = 200
    ): Response<List<UpbitCandleResponse>>

    // 일봉 데이터
    @GET("candles/days")
    suspend fun getCandlesDays(
        @Query("market") market: String,
        @Query("count") count: Int = 200
    ): Response<List<UpbitCandleResponse>>

    // 주봉 데이터
    @GET("candles/weeks")
    suspend fun getCandlesWeeks(
        @Query("market") market: String,
        @Query("count") count: Int = 200
    ): Response<List<UpbitCandleResponse>>
}