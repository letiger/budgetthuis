package nl.budgetthuis.compose.androidapp.data.repository

import nl.budgetthuis.compose.androidapp.data.remote.dto.CoinDetailDto
import nl.budgetthuis.compose.androidapp.data.remote.dto.CoinDto
import nl.budgetthuis.compose.androidapp.data.remote.CoinPaprikaApi
import nl.budgetthuis.compose.androidapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinByDetail(coinId: String): CoinDetailDto {
        return api.getCoinDetail(coinId)
    }
}