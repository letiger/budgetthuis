package nl.budgetthuis.compose.androidapp.presentation.features.coindetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import nl.budgetthuis.compose.androidapp.data.remote.dto.TeamMember
import nl.budgetthuis.compose.androidapp.domain.model.Coin
import nl.budgetthuis.compose.androidapp.domain.model.CoinDetail
import nl.budgetthuis.compose.androidapp.presentation.features.coinlist.CoinListScreen
import nl.budgetthuis.compose.androidapp.presentation.features.coinlist.CoinListState
import nl.budgetthuis.compose.androidapp.presentation.theme.AndroidAppTheme

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TeamListItemPreview() {
    val teamMember = TeamMember(
        id = "id",
        name = "Brandon",
        position = "Android Engineer"
    )
    AndroidAppTheme {
        Surface { TeamListItem(teamMember) }
    }
}