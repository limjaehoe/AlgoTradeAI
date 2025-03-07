package com.androidkotlin.algotradeai.domain.usecase

import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.repository.KoreaExchangeRepository
import javax.inject.Inject

/**
 * 한국 거래소의 코인 가격 정보를 가져오는 UseCase
 *
 * 이 UseCase는 한국 거래소(업비트, 빗썸 등)의 코인 가격 정보를
 * MultiExchangeRepository로부터 가져오는 역할을 담당합니다.
 */
class GetKoreanCoinPricesUseCase @Inject constructor(
    private val koreaExchangeRepository: KoreaExchangeRepository
) {
    /**
     * @return 한국 거래소의 코인 가격 정보 리스트
     */
    suspend operator fun invoke(): List<Coin> {
        // 단순히 Repository에 위임하는 형태지만,
        // 필요에 따라 여기서 데이터를 필터링하거나 변환할 수 있습니다.

        return koreaExchangeRepository.getKoreanCoinPrices()
    }
}