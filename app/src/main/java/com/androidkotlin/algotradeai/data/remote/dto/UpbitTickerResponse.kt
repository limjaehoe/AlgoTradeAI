package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UpbitTickerResponse(
    // 마켓 코드 (예: KRW-BTC): 거래 통화와 코인 구분
    @SerializedName("market") val market: String,

    // 현재 거래 가격: 실시간 시세 반영
    @SerializedName("trade_price") val tradePrice: Double,

    // 24시간 변동률: 빠른 시장 상황 파악
    @SerializedName("change_rate") val changeRate: Double,

    // 거래량: 유동성과 거래 활성화 정도 표현
    @SerializedName("trade_volume") val tradeVolume: Double
)