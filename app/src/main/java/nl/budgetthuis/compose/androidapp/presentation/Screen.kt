package nl.budgetthuis.compose.androidapp.presentation

import nl.budgetthuis.compose.androidapp.common.Constants.COIN_DETAILS_SCREEN_ROUTE
import nl.budgetthuis.compose.androidapp.common.Constants.COIN_LIST_SCREEN_ROUTE

sealed class Screen(val route: String) {
    object CoinListScreen : Screen(COIN_LIST_SCREEN_ROUTE)
    object CoinDetailsScreen : Screen(COIN_DETAILS_SCREEN_ROUTE) {
        const val NAV_ARG_COIN_ID = "coinId"
    }
}