package com.androidkotlin.algotradeai.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.domain.usecase.GetGlobalCoinMarketsUseCase
import com.androidkotlin.algotradeai.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * 코인 시장 정보를 관리하는 ViewModel
 *
 * 이 ViewModel은 UseCase를 통해 데이터를 가져오고 UI 상태를 관리합니다.
 * Repository를 직접 참조하지 않고 UseCase를 통해 비즈니스 로직에 접근합니다.
 */
@HiltViewModel
class CoinViewModel @Inject constructor(
    //private val coinRepository: CoinRepository,
    // Repository 대신 UseCase를 주입받습니다.
    private val getGlobalCoinMarketsUseCase: GetGlobalCoinMarketsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _coinMarkets = MutableStateFlow<List<Coin>>(emptyList())
    val coinMarkets: StateFlow<List<Coin>> = _coinMarkets.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    /**
     * 코인 시장 정보를 가져옵니다.
     *
     * UseCase를 호출하여 데이터를 가져오고 UI 상태를 업데이트합니다.
     * 네트워크 연결 여부를 확인하고 에러 처리도 수행합니다.
     */
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
                // Repository 대신 UseCase를 호출합니다.
                // 함수처럼 직접 호출할 수 있어 코드가 더 간결해집니다.
                val markets = getGlobalCoinMarketsUseCase()
                _coinMarkets.value = markets
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching coin markets: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}