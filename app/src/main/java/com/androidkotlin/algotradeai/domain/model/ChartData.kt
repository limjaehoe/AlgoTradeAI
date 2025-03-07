package com.androidkotlin.algotradeai.domain.model

import java.util.Date

/**
 * 차트 데이터 항목을 나타내는 도메인 모델
 */
data class ChartEntry(
    val timestamp: Date,
    val value: Double
)

/**
 * 차트 데이터를 나타내는 도메인 모델
 */
data class ChartData(
    val priceEntries: List<ChartEntry>,
    val volumeEntries: List<ChartEntry>
)