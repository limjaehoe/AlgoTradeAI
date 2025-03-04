package com.androidkotlin.algotradeai.di

import com.androidkotlin.algotradeai.data.repository.CoinRepositoryImpl
import com.androidkotlin.algotradeai.data.repository.MultiExchangeRepositoryImpl
import com.androidkotlin.algotradeai.domain.repository.CoinRepository
import com.androidkotlin.algotradeai.domain.repository.MultiExchangeRepository
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
        coinRepositoryImpl: CoinRepositoryImpl
    ): CoinRepository = coinRepositoryImpl

    @Provides
    @Singleton
    fun bindMultiExchangeRepository(
        multiExchangeRepositoryImpl: MultiExchangeRepositoryImpl
    ): MultiExchangeRepository = multiExchangeRepositoryImpl

}