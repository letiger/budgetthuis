package nl.budgetthuis.compose.androidapp.presentation.features.coindetails

import nl.budgetthuis.compose.androidapp.domain.model.CoinDetail

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String? = null
)
