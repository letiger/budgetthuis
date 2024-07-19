package nl.budgetthuis.compose.androidapp.presentation.features.coindetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nl.budgetthuis.compose.androidapp.common.Constants.PARAM_COIN_ID
import nl.budgetthuis.compose.androidapp.common.Resource
import nl.budgetthuis.compose.androidapp.domain.usecase.GetCoinDetailUseCase
import nl.budgetthuis.compose.androidapp.presentation.Screen
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetail: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(Screen.CoinDetailsScreen.NAV_ARG_COIN_ID)?.let(::getCoins)
    }

    private fun getCoins(coinId: String) {
        getCoinDetail(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = CoinDetailsState(isLoading = true)
                is Resource.Error -> _state.value = CoinDetailsState(error = result.message)
                is Resource.Success -> _state.value = CoinDetailsState(coin = result.data)
            }
        }.launchIn(viewModelScope)
    }
}
