package com.androidkotlin.algotradeai.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinGeckoDetailResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val description: Map<String, String>, // 여러 언어로 된 설명
    @SerializedName("image")
    val images: CoinImage,
    @SerializedName("market_data")
    val marketData: MarketData,
    val links: Links
)

data class CoinImage(
    val thumb: String,
    val small: String,
    val large: String
)

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: Map<String, Double>,
    @SerializedName("market_cap")
    val marketCap: Map<String, Double>,
    @SerializedName("total_volume")
    val totalVolume: Map<String, Double>,
    @SerializedName("high_24h")
    val high24h: Map<String, Double>,
    @SerializedName("low_24h")
    val low24h: Map<String, Double>,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double
)

data class Links(
    val homepage: List<String>,
    @SerializedName("blockchain_site")
    val blockchainSite: List<String>,
    @SerializedName("official_forum_url")
    val officialForumUrl: List<String>,
    @SerializedName("subreddit_url")
    val subredditUrl: String,
    @SerializedName("twitter_screen_name")
    val twitterScreenName: String,
    @SerializedName("telegram_channel_identifier")
    val telegramChannelIdentifier: String
)
