package nl.budgetthuis.compose.androidapp.data.remote

import nl.budgetthuis.compose.androidapp.data.remote.dto.CoinDetailDto
import nl.budgetthuis.compose.androidapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetail(
        @Path("coinId") coinId: String
    ) : CoinDetailDto
}