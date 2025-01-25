package hongsunghwi.portfolio

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import hongsunghwi.portfolio.feature.home.HomeRoute
import hongsunghwi.portfolio.feature.home.HomeScreen

@Composable
fun App() {
    PortfolioTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = {

            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HomeRoute
            ) {
                composable<HomeRoute> {
                    HomeScreen(innerPadding = innerPadding)
                }
            }
        }
    }
}