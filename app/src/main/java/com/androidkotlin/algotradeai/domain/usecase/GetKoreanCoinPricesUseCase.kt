package com.androidkotlin.algotradeai.domain.usecase

import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.repository.MultiExchangeRepository
import javax.inject.Inject

/**
 * 한국 거래소의 코인 가격 정보를 가져오는 UseCase
 *
 * 이 UseCase는 한국 거래소(업비트, 빗썸 등)의 코인 가격 정보를
 * MultiExchangeRepository로부터 가져오는 역할을 담당합니다.
 */
class GetKoreanCoinPricesUseCase @Inject constructor(
    private val multiExchangeRepository: MultiExchangeRepository
) {
    /**
     * @return 한국 거래소의 코인 가격 정보 리스트
     */
    suspend operator fun invoke(): List<Coin> {
        return multiExchangeRepository.getKoreanCoinPrices()
    }
}