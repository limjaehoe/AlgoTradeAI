package com.androidkotlin.algotradeai.data.remote.dto

data class CoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val market_cap: Double,
    val market_cap_rank: Int,
    val fully_diluted_valuation: Double?,
    val total_volume: Double,
    val price_change_percentage_24h: Double,
    val market_cap_change_percentage_24h: Double
)
