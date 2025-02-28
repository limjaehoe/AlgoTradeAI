package com.androidkotlin.algotradeai.domain

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val currentPrice: Double,
    val priceChangePercentage: Double
)
