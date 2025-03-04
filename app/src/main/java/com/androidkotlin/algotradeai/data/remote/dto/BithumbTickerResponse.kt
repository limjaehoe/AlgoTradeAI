package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BithumbTickerResponse(
    // 시가: 특정 기간 시작 가격, 가격 추세 분석에 활용
    @SerializedName("opening_price") val openingPrice: String,

    // 종가: 특정 기간 마지막 가격, 일간 성과 측정
    @SerializedName("closing_price") val closingPrice: String,

    // 최저가: 가격 변동성 분석, 리스크 평가
    @SerializedName("min_price") val minPrice: String,

    // 최고가: 상승 잠재력, 투자 심리 지표
    @SerializedName("max_price") val maxPrice: String
)