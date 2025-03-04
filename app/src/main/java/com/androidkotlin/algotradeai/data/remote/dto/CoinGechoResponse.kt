package com.androidkotlin.algotradeai.data.remote.dto

data class CoinGechoResponse(
    // 코인 고유 식별자: 글로벌 식별
    val id: String,

    // 코인 심볼: 간략한 코인 표기
    val symbol: String,

    // 코인 전체 이름: 가독성
    val name: String,

    // 코인 로고 이미지: 시각적 인식
    val image: String,

    // 현재 가격: 실시간 시세
    val current_price: Double,

    // 시가총액: 코인 규모 평가
    val market_cap: Double,

    // 시가총액 순위: 시장 내 위치
    val market_cap_rank: Int,

    // 완전 희석 가치: 잠재적 시가총액
    val fully_diluted_valuation: Double?,

    // 거래량: 유동성 지표
    val total_volume: Double,

    // 24시간 가격 변동률: 단기 성과
    val price_change_percentage_24h: Double,

    // 시가총액 변동률: 시장 동향
    val market_cap_change_percentage_24h: Double
)
