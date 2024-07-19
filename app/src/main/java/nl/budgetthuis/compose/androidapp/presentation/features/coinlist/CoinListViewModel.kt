package nl.budgetthuis.compose.androidapp.presentation.features.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nl.budgetthuis.compose.androidapp.common.Resource
import nl.budgetthuis.compose.androidapp.domain.usecase.GetCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = CoinListState(isLoading = true)
                is Resource.Error -> _state.value = CoinListState(error = result.message)
                is Resource.Success -> _state.value =
                    CoinListState(coins = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}
