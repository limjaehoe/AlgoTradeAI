package com.androidkotlin.algotradeai

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.androidkotlin.algotradeai.presentation.ui.screens.GlobalCoinScreen
import com.androidkotlin.algotradeai.presentation.ui.screens.KoreaExchangeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                ExchangeApp()
            }
        }
    }
}

@Composable
fun ExchangeApp() {
    // 선택된 탭 상태 관리
    var selectedTabIndex by remember { mutableStateOf(0) }

    // 탭 목록 정의
    val tabs = listOf("한국 거래소", "글로벌 거래소")

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            // 선택된 탭에 따라 다른 화면 표시
            when (selectedTabIndex) {
                0 -> KoreaExchangeScreen() // 한국 거래소
                1 -> GlobalCoinScreen()    // 글로벌 거래소 (코인게코)
            }
        }
    }
}
