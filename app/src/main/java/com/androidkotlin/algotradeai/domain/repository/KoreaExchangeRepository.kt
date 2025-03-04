package com.androidkotlin.algotradeai.domain.repository

import com.androidkotlin.algotradeai.domain.model.Coin

interface KoreaExchangeRepository {
    suspend fun getKoreanCoinPrices(): List<Coin>
}