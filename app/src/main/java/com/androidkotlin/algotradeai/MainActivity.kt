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
import androidx.navigation.compose.rememberNavController
import com.androidkotlin.algotradeai.presentation.ui.navigation.NavRoutes
import com.androidkotlin.algotradeai.presentation.ui.navigation.SetupNavGraph
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
    // NavController 생성
    val navController = rememberNavController()

    // 선택된 탭 상태 관리
    var selectedTabIndex by remember { mutableStateOf(0) }
    /*
    remember: Composable이 재구성될 때 상태를 유지합니다.
    mutableStateOf: 관찰 가능한 상태를 생성합니다.
    by 키워드: 프로퍼티 위임을 통해 .value를 직접 쓰지 않고 값에 접근할 수 있게 합니다.
     */

    // 탭 목록 정의
    val tabs = listOf("한국 거래소", "글로벌 거래소")

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            // 탭 변경 시 해당 경로로 네비게이션
                            when(index) {
                                0 -> navController.navigate(NavRoutes.KOREA_EXCHANGE) {
                                    // 백 스택 관리
                                    popUpTo(NavRoutes.KOREA_EXCHANGE) { inclusive = true }
                                    launchSingleTop = true
                                }
                                1 -> navController.navigate(NavRoutes.GLOBAL_COIN) {
                                    popUpTo(NavRoutes.GLOBAL_COIN) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        },
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
            // NavHost 설정
            SetupNavGraph(
                navController = navController,
                // 기본 시작 화면을 한국 거래소로 설정
                startDestination = NavRoutes.KOREA_EXCHANGE
            )
        }
    }
}