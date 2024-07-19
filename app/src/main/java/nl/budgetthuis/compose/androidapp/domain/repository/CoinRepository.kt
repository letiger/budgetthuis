package nl.budgetthuis.compose.androidapp.domain.repository

import nl.budgetthuis.compose.androidapp.data.remote.dto.CoinDetailDto
import nl.budgetthuis.compose.androidapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinByDetail(coinId: String): CoinDetailDto
}