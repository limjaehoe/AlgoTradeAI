package com.androidkotlin.algotradeai.domain.repository

import com.androidkotlin.algotradeai.domain.model.Coin

interface GlobalCoinRepository {
    suspend fun getGlobalCoinMarkets(): List<Coin>
}