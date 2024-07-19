package nl.budgetthuis.compose.androidapp.presentation.features.coinlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import nl.budgetthuis.compose.androidapp.domain.model.Coin
import nl.budgetthuis.compose.androidapp.presentation.theme.AndroidAppTheme

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(coin) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.headlineMedium,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = if(coin.isActive) "active" else "inactive",
            color = if(coin.isActive) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListItemPreview() {
    val previewCoins = List(1) {
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
            CoinListItem(
                coin = previewCoins.first(),
                onItemClick = {}
            )
        }
    }
}