package com.androidkotlin.algotradeai.data.remote.dto

data class CoinGeckoMarketChartResponse(
    val prices: List<List<Double>>, // [timestamp, price]
    val market_caps: List<List<Double>>, // [timestamp, market_cap]
    val total_volumes: List<List<Double>> // [timestamp, volume]
)