package nl.budgetthuis.compose.androidapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import nl.budgetthuis.compose.androidapp.presentation.features.coindetails.CoinDetailScreenContainer
import nl.budgetthuis.compose.androidapp.presentation.features.coinlist.CoinListScreenContainer
import nl.budgetthuis.compose.androidapp.presentation.theme.AndroidAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(route = Screen.CoinListScreen.route) {
                            CoinListScreenContainer(navController = navController)
                        }
                        composable(
                            route = Screen.CoinDetailsScreen.route + "/{${Screen.CoinDetailsScreen.NAV_ARG_COIN_ID}}",
                            arguments = listOf(
                                navArgument(Screen.CoinDetailsScreen.NAV_ARG_COIN_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            CoinDetailScreenContainer()
                        }
                    }
                }
            }
        }
    }
}