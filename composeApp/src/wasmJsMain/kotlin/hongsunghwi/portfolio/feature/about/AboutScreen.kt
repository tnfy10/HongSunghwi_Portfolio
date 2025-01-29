package hongsunghwi.portfolio.feature.about

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hongsunghwi.portfolio.core.ui.component.ScrollEndDetectColumn
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@Composable
fun AboutScreen(
    innerPadding: PaddingValues,
    onNavigateToExperiences: () -> Unit
) {
    ScrollEndDetectColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        onEndScrollForward = {
            onNavigateToExperiences()
        }
    ) {
        Text(
            text = "About me",
            style = PortfolioTheme.typography.displayLarge
        )
    }
}