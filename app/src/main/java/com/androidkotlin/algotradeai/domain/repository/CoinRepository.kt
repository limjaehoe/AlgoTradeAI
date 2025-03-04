package com.androidkotlin.algotradeai.domain.repository

import com.androidkotlin.algotradeai.domain.model.Coin

interface CoinRepository {
    suspend fun getCoinMarkets(): List<Coin>
}