package nl.budgetthuis.compose.androidapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import nl.budgetthuis.compose.androidapp.domain.model.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("is_new") val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin() = Coin(
    id,
    isActive,
    name,
    rank,
    symbol
)