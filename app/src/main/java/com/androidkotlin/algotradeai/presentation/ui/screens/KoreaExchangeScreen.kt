package com.androidkotlin.algotradeai.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.presentation.viewmodel.KoreaExchangeViewModel
import java.text.NumberFormat
import java.util.Locale

enum class KoreanExchange {
    UPBIT, BITHUMB, ALL
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KoreaExchangeScreen(
    viewModel: KoreaExchangeViewModel = hiltViewModel(),
    onCoinClick: (String) -> Unit = {}  // 코인 클릭 콜백 추가
) {
    val koreanCoinPrices by viewModel.koreanCoinPrices.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // 선택된 거래소 상태 관리
    var selectedExchange by remember { mutableStateOf(KoreanExchange.ALL) }



    Scaffold(
        topBar = {
            // TopAppBar를 제거하고 필터링 기능 추가
            Column {
                // 거래소 필터링 버튼
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterChip(
                        selected = selectedExchange == KoreanExchange.ALL,
                        onClick = { selectedExchange = KoreanExchange.ALL },
                        label = { Text("전체") }
                    )
                    FilterChip(
                        selected = selectedExchange == KoreanExchange.UPBIT,
                        onClick = { selectedExchange = KoreanExchange.UPBIT },
                        label = { Text("업비트") }
                    )
                    FilterChip(
                        selected = selectedExchange == KoreanExchange.BITHUMB,
                        onClick = { selectedExchange = KoreanExchange.BITHUMB },
                        label = { Text("빗썸") }
                    )
                }

                // 헤더 타이틀
                Text(
                    text = "한국 거래소 코인 시세",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            }
        }
    ) { padding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            koreanCoinPrices.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("데이터를 불러올 수 없습니다.")
                }
            }
            else -> {
                // 선택된 거래소에 따라 코인 필터링
                val filteredCoins = when (selectedExchange) {
                    KoreanExchange.UPBIT -> koreanCoinPrices.filter { it.id.startsWith("KRW-") }
                    KoreanExchange.BITHUMB -> koreanCoinPrices.filter { it.id.startsWith("bithumb-") }
                    KoreanExchange.ALL -> koreanCoinPrices
                }

                // 로그로 필터링 결과 확인
                Log.d("MultiExchangeScreen", "Selected exchange: $selectedExchange")
                Log.d("MultiExchangeScreen", "Total coins: ${koreanCoinPrices.size}, Filtered coins: ${filteredCoins.size}")

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    items(filteredCoins) { coin ->
                        CoinExchangeItem(
                            coin = coin,
                            onClick = { onCoinClick(coin.id) }  // 클릭 콜백 호출
                        )
                    }
                }


            }
        }
    }


}

@Composable
fun CoinExchangeItem(
    coin: Coin,
    onClick: () -> Unit = {}  // 클릭 콜백 추가
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)
    numberFormat.maximumFractionDigits = 2

    // 거래소 정보 추출 (예: "KRW-BTC" -> "업비트", "bithumb-BTC" -> "빗썸")
    val exchange = when {
        coin.id.startsWith("KRW-") -> "업비트"
        coin.id.contains("bithumb", ignoreCase = true) -> "빗썸"
        else -> ""
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },  // 클릭 가능하게 수정,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = coin.symbol.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = exchange,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Text(
                    text = "${numberFormat.format(coin.currentPrice)} 원",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "변동률: ${String.format("%.2f", coin.priceChangePercentage)}%",
                color = if (coin.priceChangePercentage >= 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
