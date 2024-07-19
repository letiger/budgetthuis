package nl.budgetthuis.compose.androidapp.presentation.features.coinlist

import nl.budgetthuis.compose.androidapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String? = null
)
