package com.androidkotlin.algotradeai.di

import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import com.androidkotlin.algotradeai.domain.repository.KoreaExchangeRepository
import com.androidkotlin.algotradeai.domain.usecase.AnalyzeCoinTrendsUseCase
import com.androidkotlin.algotradeai.domain.usecase.GetCoinChartUseCase
import com.androidkotlin.algotradeai.domain.usecase.GetCoinDetailUseCase
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

    // provide 함수
    // 주로 구체적인 클래스의 인스턴스를 생성하여 제공할 때 사용합니다.
    // 코드에서는 provideGetCoinMarketsUseCase 등의 함수가 UseCase 클래스의 새 인스턴스를 생성하여 반환합니다.

    // 인터페이스와 구현체 관계에서는 주로 bind를, 구체 클래스를 직접 생성할 때는 주로 provide를 사용하는 패턴을 따르고 있습니다.

    @Provides
    @Singleton
    fun provideGetCoinMarketsUseCase(
        globalcoinRepository: GlobalCoinRepository
    ): GetGlobalCoinMarketsUseCase {
        return GetGlobalCoinMarketsUseCase(globalcoinRepository)
    }

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
        globalcoinRepository: GlobalCoinRepository
    ): AnalyzeCoinTrendsUseCase {
        return AnalyzeCoinTrendsUseCase(globalcoinRepository)
    }

    @Provides
    @Singleton
    fun provideGetCoinDetailUseCase(
        globalCoinRepository: GlobalCoinRepository,
        koreaExchangeRepository: KoreaExchangeRepository
    ): GetCoinDetailUseCase {
        return GetCoinDetailUseCase(globalCoinRepository, koreaExchangeRepository)
    }

    @Provides
    @Singleton
    fun provideGetCoinChartUseCase(
        globalCoinRepository: GlobalCoinRepository,
        koreaExchangeRepository: KoreaExchangeRepository
    ): GetCoinChartUseCase {
        return GetCoinChartUseCase(globalCoinRepository, koreaExchangeRepository)
    }
}