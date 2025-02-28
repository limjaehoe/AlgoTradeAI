package com.androidkotlin.algotradeai.data.remote.api

import com.androidkotlin.algotradeai.data.remote.dto.UpbitTickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApiService {
    @GET("ticker")
    suspend fun getCoinTickers(
        @Query("markets") markets: List<String>
    ): Response<List<UpbitTickerResponse>>
}