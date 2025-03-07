package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UpbitCandleResponse(
    // 마켓명
    @SerializedName("market")
    val market: String,

    // 캔들 기준 시각 (UTC)
    @SerializedName("candle_date_time_utc")
    val candleDateTimeUtc: String,

    // 캔들 기준 시각 (KST)
    @SerializedName("candle_date_time_kst")
    val candleDateTimeKst: String,

    // 시가
    @SerializedName("opening_price")
    val openingPrice: Double,

    // 고가
    @SerializedName("high_price")
    val highPrice: Double,

    // 저가
    @SerializedName("low_price")
    val lowPrice: Double,

    // 종가
    @SerializedName("trade_price")
    val tradePrice: Double,

    // 마지막 틱이 저장된 시각
    @SerializedName("timestamp")
    val timestamp: Long,

    // 누적 거래 금액
    @SerializedName("candle_acc_trade_price")
    val candleAccTradePrice: Double,

    // 누적 거래량
    @SerializedName("candle_acc_trade_volume")
    val candleAccTradeVolume: Double
)