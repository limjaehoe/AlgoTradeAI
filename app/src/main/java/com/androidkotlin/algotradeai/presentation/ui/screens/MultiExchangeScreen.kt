package com.androidkotlin.algotradeai.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidkotlin.algotradeai.domain.model.Coin
import com.androidkotlin.algotradeai.presentation.viewmodel.MultiExchangeViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiExchangeScreen(
    viewModel: MultiExchangeViewModel = hiltViewModel()
) {
    val koreanCoinPrices by viewModel.koreanCoinPrices.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("한국 거래소 코인 시세") }
            )
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    items(koreanCoinPrices) { coin ->
                        CoinExchangeItem(coin)
                    }
                }
            }
        }
    }
}

@Composable
fun CoinExchangeItem(coin: Coin) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)
    numberFormat.maximumFractionDigits = 2

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${numberFormat.format(coin.currentPrice)} 원", // 원화 표시 추가
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