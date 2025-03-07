package com.androidkotlin.algotradeai.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.usecase.AnalyzeCoinTrendsUseCase
import com.androidkotlin.algotradeai.domain.usecase.GetKoreanCoinPricesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 한국 거래소의 코인 가격 정보를 관리하는 ViewModel
 *
 * 이 ViewModel은 UseCase를 통해 한국 거래소의 코인 가격 데이터를 가져오고
 * UI 상태를 관리합니다.
 */
@HiltViewModel
class KoreaExchangeViewModel @Inject constructor(
    // Repository 대신 UseCase를 주입받습니다. 별도의 @provides 추가안해도 됨.
    private val getKoreanCoinPricesUseCase: GetKoreanCoinPricesUseCase,
) : ViewModel() {
    private val _koreanCoinPrices = MutableStateFlow<List<Coin>>(emptyList())
    val koreanCoinPrices: StateFlow<List<Coin>> = _koreanCoinPrices.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchKoreanCoinPrices()
    }

    /**
     * 한국 거래소의 코인 가격 정보를 가져옵니다.
     *
     * UseCase를 호출하여 데이터를 가져오고 UI 상태를 업데이트합니다.
     */
    fun fetchKoreanCoinPrices() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Repository 대신 UseCase를 호출합니다.
                val prices = getKoreanCoinPricesUseCase()


                Log.d("MultiExchangeViewModel", "Fetched ${prices.size} coins")
                _koreanCoinPrices.value = prices
            } catch (e: Exception) {
                Log.e("MultiExchangeViewModel", "Error fetching prices", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}