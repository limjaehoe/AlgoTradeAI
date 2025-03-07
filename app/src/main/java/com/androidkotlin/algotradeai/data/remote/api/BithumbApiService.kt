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

    // 코인 상세 정보 요청 메서드 추가 - 빗썸에서는 ticker API가 상세 정보도 제공
    @GET("ticker/{order_currency}_{payment_currency}")
    suspend fun getCoinDetail(
        @Path("order_currency") orderCurrency: String,
        @Path("payment_currency") paymentCurrency: String = "KRW"
    ): BithumbResponse<BithumbTickerData>

    // 캔들스틱 데이터 요청 메서드 추가
    @GET("candlestick/{order_currency}_{payment_currency}/{chart_intervals}")
    suspend fun getCandlestick(
        @Path("order_currency") orderCurrency: String,
        @Path("payment_currency") paymentCurrency: String = "KRW",
        @Path("chart_intervals") chartIntervals: String = "24h" // 1m, 3m, 5m, 10m, 30m, 1h, 6h, 12h, 24h
    ): BithumbResponse<List<Array<String>>>
}