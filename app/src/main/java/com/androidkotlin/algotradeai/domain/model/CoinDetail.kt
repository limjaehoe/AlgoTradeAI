package com.androidkotlin.algotradeai.domain.model

/**
 * 코인의 상세 정보를 나타내는 도메인 모델
 */
data class CoinDetail(
    val id: String,
    val symbol: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val currentPrice: Double,
    val marketCap: Double,
    val volume24h: Double,
    val high24h: Double,
    val low24h: Double,
    val priceChangePercentage24h: Double,
    val marketCapChangePercentage24h: Double,
    val website: String,
    val twitter: String,
    val reddit: String,
    val isKoreanExchange: Boolean,
    val exchange: String
)