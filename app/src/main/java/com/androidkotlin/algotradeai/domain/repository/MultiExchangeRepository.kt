package com.androidkotlin.algotradeai.domain.repository

import com.androidkotlin.algotradeai.domain.model.Coin

interface MultiExchangeRepository {
    suspend fun getKoreanCoinPrices(): List<Coin>
}