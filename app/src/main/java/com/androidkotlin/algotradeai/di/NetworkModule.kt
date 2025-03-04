package com.androidkotlin.algotradeai.di

import android.util.Log
import com.androidkotlin.algotradeai.data.remote.api.BithumbApiService
import com.androidkotlin.algotradeai.data.remote.api.CoinGeckoApiService
import com.androidkotlin.algotradeai.data.remote.api.UpbitApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoinGeckoRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UpbitRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BithumbRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val request = chain.request()
                Log.d("Network", "Sending request: ${request.url}")
                val response = chain.proceed(request)
                Log.d("Network", "Received response: ${response.code}")
                response
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @CoinGeckoRetrofit
    fun provideCoinGeckoRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @UpbitRetrofit
    fun provideUpbitRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @BithumbRetrofit
    fun provideBithumbRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.bithumb.com/public/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoApiService(@CoinGeckoRetrofit retrofit: Retrofit): CoinGeckoApiService {
        return retrofit.create(CoinGeckoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUpbitApiService(@UpbitRetrofit retrofit: Retrofit): UpbitApiService {
        return retrofit.create(UpbitApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBithumbApiService(@BithumbRetrofit retrofit: Retrofit): BithumbApiService {
        return retrofit.create(BithumbApiService::class.java)
    }
}