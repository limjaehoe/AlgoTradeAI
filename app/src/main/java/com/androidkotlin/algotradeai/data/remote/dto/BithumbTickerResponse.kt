package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BithumbTickerResponse(
    @SerializedName("opening_price") val openingPrice: String,
    @SerializedName("closing_price") val closingPrice: String,
    @SerializedName("min_price") val minPrice: String,
    @SerializedName("max_price") val maxPrice: String
)