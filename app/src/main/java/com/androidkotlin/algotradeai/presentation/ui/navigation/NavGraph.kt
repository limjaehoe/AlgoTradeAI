package com.androidkotlin.algotradeai.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androidkotlin.algotradeai.presentation.ui.screens.CoinDetailScreen
import com.androidkotlin.algotradeai.presentation.ui.screens.GlobalCoinScreen
import com.androidkotlin.algotradeai.presentation.ui.screens.KoreaExchangeScreen

// 네비게이션 라우트 정의
object NavRoutes {
    const val KOREA_EXCHANGE = "korea_exchange"
    const val GLOBAL_COIN = "global_coin"
    const val COIN_DETAIL = "coin_detail"
}

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // 한국 거래소 화면
        composable(route = NavRoutes.KOREA_EXCHANGE) {
            KoreaExchangeScreen(
                onCoinClick = { coinId ->
                    // 클릭된 코인의 ID와 함께 상세 화면으로 이동
                    navController.navigate("${NavRoutes.COIN_DETAIL}/$coinId")
                }
            )
        }

        // 글로벌 거래소 화면
        composable(route = NavRoutes.GLOBAL_COIN) {
            GlobalCoinScreen(
                onCoinClick = { coinId ->
                    navController.navigate("${NavRoutes.COIN_DETAIL}/$coinId")
                }
            )
        }

        // 코인 상세 화면 - 경로에 코인 ID 포함
        composable(
            route = "${NavRoutes.COIN_DETAIL}/{coinId}",
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
            CoinDetailScreen(
                coinId = coinId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}