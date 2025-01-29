package hongsunghwi.portfolio

import androidx.compose.runtime.Composable
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import hongsunghwi.portfolio.feature.main.MainScreen

@Composable
fun App() {
    PortfolioTheme {
        MainScreen()
    }
}