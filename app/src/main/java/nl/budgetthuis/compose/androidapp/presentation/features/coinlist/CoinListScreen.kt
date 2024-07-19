package nl.budgetthuis.compose.androidapp.presentation.features.coinlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nl.budgetthuis.compose.androidapp.domain.model.Coin
import nl.budgetthuis.compose.androidapp.presentation.Screen
import nl.budgetthuis.compose.androidapp.presentation.theme.AndroidAppTheme


@Composable
fun CoinListScreenContainer(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val coinListState = viewModel.state.value
    CoinListScreen(navController, coinListState)
}

@Composable
fun CoinListScreen(
    navController: NavController,
    state: CoinListState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(Screen.CoinDetailsScreen.route + "/${coin.id}")
                    }
                )
            }
        }
        if (state.error?.isNotBlank() == true) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListPreview() {
    val navController = rememberNavController()
    val previewCoins = List(4) {
        Coin(
            id = it.toString(),
            isActive = true,
            name = "name: $it",
            rank = it,
            symbol = "symbol: $it"
        )
    }
    AndroidAppTheme {
        Surface {
            CoinListScreen(navController, CoinListState(coins = previewCoins))
        }
    }
}