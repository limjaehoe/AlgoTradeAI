package com.androidkotlin.algotradeai.domain

interface CoinRepository {
    suspend fun getCoinMarkets(): List<Coin>
}