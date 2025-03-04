package com.androidkotlin.algotradeai.di

import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import com.androidkotlin.algotradeai.domain.repository.KoreaExchangeRepository
import com.androidkotlin.algotradeai.domain.usecase.AnalyzeCoinTrendsUseCase
import com.androidkotlin.algotradeai.domain.usecase.GetGlobalCoinMarketsUseCase
import com.androidkotlin.algotradeai.domain.usecase.GetKoreanCoinPricesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * UseCase 의존성 주입을 위한 Hilt 모듈
 *
 * 이 모듈은 애플리케이션에서 사용되는 모든 UseCase의 인스턴스를 제공합니다.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    /**
     * GetCoinMarketsUseCase 인스턴스를 제공합니다.
     *
     * @param coinRepository UseCase에서 사용할 CoinRepository
     * @return GetCoinMarketsUseCase 인스턴스
     */
    @Provides
    @Singleton
    fun provideGetCoinMarketsUseCase(
        coinRepository: GlobalCoinRepository
    ): GetGlobalCoinMarketsUseCase {
        return GetGlobalCoinMarketsUseCase(coinRepository)
    }

    /**
     * GetKoreanCoinPricesUseCase 인스턴스를 제공합니다.
     *
     * @param koreaExchangeRepository UseCase에서 사용할 MultiExchangeRepository
     * @return GetKoreanCoinPricesUseCase 인스턴스
     */
    @Provides
    @Singleton
    fun provideGetKoreanCoinPricesUseCase(
        koreaExchangeRepository: KoreaExchangeRepository
    ): GetKoreanCoinPricesUseCase {
        return GetKoreanCoinPricesUseCase(koreaExchangeRepository)
    }

    @Provides
    @Singleton
    fun provideAnalyzeCoinTrendsUseCase(
        coinRepository: GlobalCoinRepository
    ): AnalyzeCoinTrendsUseCase {
        return AnalyzeCoinTrendsUseCase(coinRepository)
    }
}