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

    // bind 함수
    // 주로 인터페이스와 그 구현체를 연결할 때 사용
    // 인터페이스 - 구현체 말고는 안쓰는듯 하다?
    // ex) coinrepository = coinrepositoryimpl 연동!

    @Provides
    @Singleton
    fun bindCoinRepository(
        globalCoinRepositoryImpl: GlobalCoinRepositoryImpl
    ): GlobalCoinRepository = globalCoinRepositoryImpl //구현체 반환

    @Provides
    @Singleton
    fun bindMultiExchangeRepository(
        koreaExchangeRepositoryImpl: KoreaExchangeRepositoryImpl
    ): KoreaExchangeRepository = koreaExchangeRepositoryImpl //구현체 반환

}