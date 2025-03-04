package com.androidkotlin.algotradeai.data.remote.api

import com.androidkotlin.algotradeai.data.remote.dto.BithumbResponse
import com.androidkotlin.algotradeai.data.remote.dto.BithumbTickerData
import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApiService {
    @GET("ticker/{order_currency}_{payment_currency}")
    suspend fun getCoinTicker(
        @Path("order_currency") orderCurrency: String,
        @Path("payment_currency") paymentCurrency: String = "KRW"
    ): BithumbResponse<BithumbTickerData>
}