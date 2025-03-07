package com.androidkotlin.algotradeai.presentation.ui.components

import androidx.compose.foundation.clickable
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
import com.androidkotlin.algotradeai.domain.model.Coin

//함수 파라미터: 데이터(coin)와 이벤트 핸들러(onClick)를 전달받습니다.
//Compose는 선언적 UI를 사용하므로 "어떻게" 그릴지보다 "무엇을" 그릴지에 집중합니다.
@Composable
fun CoinMarketItem(
    coin: Coin,
    onClick: () -> Unit = {}
) {
    Card(
        //Modifier는 Compose UI 요소의 크기, 패딩, 동작 등을 정의합니다.
        //체이닝 방식으로 여러 속성을 적용할 수 있습니다.
        modifier = Modifier
            .fillMaxWidth() //가능한 최대 너비를 차지
            .padding(8.dp)  //모든 방향에 8dp 패딩 추가
            .clickable { onClick() },  // 클릭이벤트 처리
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