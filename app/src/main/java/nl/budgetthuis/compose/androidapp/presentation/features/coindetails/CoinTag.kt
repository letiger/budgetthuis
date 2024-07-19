package nl.budgetthuis.compose.androidapp.presentation.features.coindetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.budgetthuis.compose.androidapp.data.remote.dto.TeamMember
import nl.budgetthuis.compose.androidapp.presentation.theme.AndroidAppTheme

@Composable
fun CoinTag(tag: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoinTagPreview() {
    val teamMember = TeamMember(
        id = "id",
        name = "Brandon",
        position = "Android Engineer"
    )
    AndroidAppTheme {
        Surface { CoinTag(tag = "Tag") }
    }
}