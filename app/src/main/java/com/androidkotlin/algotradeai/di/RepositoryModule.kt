package com.androidkotlin.algotradeai.di

import com.androidkotlin.algotradeai.data.repository.GlobalCoinRepositoryImpl
import com.androidkotlin.algotradeai.data.repository.KoreaExchangeRepositoryImpl
import com.androidkotlin.algotradeai.domain.repository.GlobalCoinRepository
import com.androidkotlin.algotradeai.domain.repository.KoreaExchangeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun bindCoinRepository(
        globalCoinRepositoryImpl: GlobalCoinRepositoryImpl
    ): GlobalCoinRepository = globalCoinRepositoryImpl

    @Provides
    @Singleton
    fun bindMultiExchangeRepository(
        koreaExchangeRepositoryImpl: KoreaExchangeRepositoryImpl
    ): KoreaExchangeRepository = koreaExchangeRepositoryImpl

}