package com.androidkotlin.algotradeai.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidkotlin.algotradeai.domain.Coin


@Composable
fun CoinMarketItem(coin: Coin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = coin.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Price: $${coin.currentPrice}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "24h Change: ${coin.priceChangePercentage}%",
                color = if (coin.priceChangePercentage >= 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}