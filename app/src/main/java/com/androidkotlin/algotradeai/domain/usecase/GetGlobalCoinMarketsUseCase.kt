package com.androidkotlin.algotradeai.domain.usecase

import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import javax.inject.Inject

/**
 * 코인 시장 정보를 가져오는 UseCase
 *
 * UseCase는 단일 책임 원칙을 따르는 클래스로, 특정 비즈니스 로직을 캡슐화합니다.
 * 이 UseCase는 전체 코인 시장 데이터를 Repository로부터 가져오는 역할을 담당합니다.
 */
class GetGlobalCoinMarketsUseCase @Inject constructor(
    private val globalcoinRepository: GlobalCoinRepository
) {
    /**
     * UseCase를 함수처럼 호출할 수 있도록 invoke 연산자 함수를 구현합니다.
     * 이렇게 하면 useCase()와 같이 간결하게 호출할 수 있습니다.
     *
     * @return 코인 시장 정보 리스트
     */
    suspend operator fun invoke(): List<Coin> {
        // 단순히 Repository에 위임하는 형태지만,
        // 필요에 따라 여기서 데이터를 필터링하거나 변환할 수 있습니다.
        return globalcoinRepository.getGlobalCoinMarkets()
    }

}