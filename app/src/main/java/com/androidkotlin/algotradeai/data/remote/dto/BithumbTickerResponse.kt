package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

// 빗썸 API 응답 형식에 맞는 클래스들 추가
data class BithumbResponse<T>(
    val status: String,
    val data: T
)

data class BithumbTickerData(
    val opening_price: String,
    val closing_price: String,
    val min_price: String,
    val max_price: String,
    val units_traded: String,
    val acc_trade_value: String,
    val prev_closing_price: String,
    val fluctate_24H: String,
    val fluctate_rate_24H: String
)