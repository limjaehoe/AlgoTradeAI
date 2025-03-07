package com.androidkotlin.algotradeai.domain.usecase

import android.util.Log
import com.androidkotlin.algotradeai.domain.model.ChartData
import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import com.androidkotlin.algotradeai.domain.repository.KoreaExchangeRepository
import javax.inject.Inject

class GetCoinChartUseCase @Inject constructor(
    private val globalCoinRepository: GlobalCoinRepository,
    private val koreaExchangeRepository: KoreaExchangeRepository
) {
    suspend operator fun invoke(coinId: String, timeframe: String = "7d"): ChartData? {
        Log.d("GetCoinChartUseCase", "Getting chart for $coinId with timeframe $timeframe")

        // timeframe을 일수로 변환 (CoinGecko API 용)
        val days = when (timeframe) {
            "1d" -> 1
            "7d" -> 7
            "14d" -> 14
            "30d" -> 30
            "90d" -> 90
            "180d" -> 180
            "365d", "1y" -> 365
            "max" -> 1825  // 최대 약 5년
            else -> 7      // 기본값
        }

        // coinId 형식을 기반으로 거래소 확인
        return when {
            // 업비트 형식: KRW-BTC
            coinId.startsWith("KRW-") -> {
                val symbol = coinId.split("-")[1]
                koreaExchangeRepository.getKoreanCoinChart("upbit", symbol, timeframe)
            }

            // 빗썸 형식: bithumb-BTC
            coinId.startsWith("bithumb-") -> {
                val symbol = coinId.split("-")[1]
                koreaExchangeRepository.getKoreanCoinChart("bithumb", symbol, timeframe)
            }

            // 그 외는 CoinGecko로 가정
            else -> {
                globalCoinRepository.getCoinMarketChart(coinId, days)
            }
        }
    }
}