package com.androidkotlin.algotradeai.domain.usecase

import android.util.Log
import com.androidkotlin.algotradeai.domain.model.CoinDetail
import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import com.androidkotlin.algotradeai.domain.repository.KoreaExchangeRepository
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val globalCoinRepository: GlobalCoinRepository,
    private val koreaExchangeRepository: KoreaExchangeRepository
) {
    suspend operator fun invoke(coinId: String): CoinDetail? {
        Log.d("GetCoinDetailUseCase", "Getting detail for $coinId")

        // coinId 형식을 기반으로 거래소 확인
        return when {
            // 업비트 형식: KRW-BTC
            coinId.startsWith("KRW-") -> {
                val symbol = coinId.split("-")[1]
                koreaExchangeRepository.getKoreanCoinDetail("upbit", symbol)
            }

            // 빗썸 형식: bithumb-BTC
            coinId.startsWith("bithumb-") -> {
                val symbol = coinId.split("-")[1]
                koreaExchangeRepository.getKoreanCoinDetail("bithumb", symbol)
            }

            // 그 외는 CoinGecko로 가정
            else -> {
                globalCoinRepository.getCoinDetail(coinId)
            }
        }
    }
}