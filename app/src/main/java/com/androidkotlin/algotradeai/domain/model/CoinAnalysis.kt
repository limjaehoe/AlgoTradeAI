package com.androidkotlin.algotradeai.domain.model

/**
 * 코인 분석 결과를 나타내는 데이터 클래스
 */
data class CoinAnalysis(
    val coinId: String,
    val coinName: String,
    val currentPrice: Double,
    val trend: Trend,
    val signal: Signal,
    val momentum: Double
)

/**
 * 코인 가격 추세
 */
enum class Trend {
    UP, DOWN, NEUTRAL
}

/**
 * 거래 신호 (매수/매도/유지)
 */
enum class Signal {
    BUY, SELL, HOLD
}