package com.androidkotlin.algotradeai.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.repository.MultiExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultiExchangeViewModel @Inject constructor(
    private val multiExchangeRepository: MultiExchangeRepository
) : ViewModel() {
    private val _koreanCoinPrices = MutableStateFlow<List<Coin>>(emptyList())
    val koreanCoinPrices: StateFlow<List<Coin>> = _koreanCoinPrices.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchKoreanCoinPrices()
    }

    fun fetchKoreanCoinPrices() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val prices = multiExchangeRepository.getKoreanCoinPrices()
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