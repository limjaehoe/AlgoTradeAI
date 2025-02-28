package com.androidkotlin.algotradeai.data.remote.api

import com.androidkotlin.algotradeai.data.remote.dto.BithumbTickerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApiService {
    @GET("public/ticker/{coin}_KRW")
    suspend fun getCoinTicker(
        @Path("coin") coinSymbol: String
    ): BithumbTickerResponse
}