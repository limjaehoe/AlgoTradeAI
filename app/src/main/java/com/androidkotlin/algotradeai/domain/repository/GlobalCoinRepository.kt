package com.androidkotlin.algotradeai.domain.repository

import com.androidkotlin.algotradeai.domain.model.Coin

//1. 인터페이스를 정의하고, 어떤 기능이 필요한지만 정의하고, 구현방법은 명시하지 않는다.
//2. 도메인 레이어는 비즈니스 로직과 규칙만 정의하고, 데이터 레이어는 그 구현방법(API호출, 데이터베이스접근 등)을 담당한다.
interface GlobalCoinRepository {
    suspend fun getGlobalCoinMarkets(): List<Coin>
}



