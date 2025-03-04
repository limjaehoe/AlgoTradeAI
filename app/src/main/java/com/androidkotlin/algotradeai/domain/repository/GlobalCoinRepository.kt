package com.androidkotlin.algotradeai.domain.repository

import com.androidkotlin.algotradeai.domain.model.Coin

//1. 인터페이스를 정의하고, 어떤 기능이 필요한지만 정의하고, 구현방법은 명시하지 않는다.
//2. 도메인 레이어는 비즈니스 로직과 규칙만 정의하고, 데이터 레이어는 그 구현방법(API호출, 데이터베이스접근 등)을 담당한다.
interface GlobalCoinRepository {
    suspend fun getGlobalCoinMarkets(): List<Coin>
}



/*
com.project
│
├── data
│   ├── local       # 로컬 데이터베이스
│   ├── remote      # 네트워크 API
│   └── repository  # 데이터 리포지토리 구현
│
├── domain
│   ├── model       # 도메인 모델
│   ├── repository  # 추상화된 리포지토리 인터페이스
│   └── usecase     # 비즈니스 로직
│
└── presentation
    ├── ui          # 화면, 컴포넌트
    └── viewmodel   # MVVM 뷰모델
 */