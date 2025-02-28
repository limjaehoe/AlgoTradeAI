package com.androidkotlin.algotradeai.domain

interface MultiExchangeRepository {
    suspend fun getKoreanCoinPrices(): List<Coin>
}