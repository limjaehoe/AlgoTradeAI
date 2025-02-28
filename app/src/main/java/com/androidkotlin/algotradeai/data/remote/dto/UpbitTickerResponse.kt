package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UpbitTickerResponse(
    @SerializedName("market") val market: String,
    @SerializedName("trade_price") val tradePrice: Double,
    @SerializedName("change_rate") val changeRate: Double,
    @SerializedName("trade_volume") val tradeVolume: Double
)