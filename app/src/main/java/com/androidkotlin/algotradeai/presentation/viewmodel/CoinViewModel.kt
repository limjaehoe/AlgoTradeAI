package com.androidkotlin.algotradeai.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.repository.CoinRepository
import com.androidkotlin.algotradeai.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _coinMarkets = MutableStateFlow<List<Coin>>(emptyList())
    val coinMarkets: StateFlow<List<Coin>> = _coinMarkets.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun fetchCoinMarkets() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            if (!NetworkUtils.isNetworkAvailable(context)) {
                _errorMessage.value = "No network connection"
                _isLoading.value = false
                return@launch
            }

            try {
                val markets = coinRepository.getCoinMarkets()
                _coinMarkets.value = markets
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching coin markets: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}