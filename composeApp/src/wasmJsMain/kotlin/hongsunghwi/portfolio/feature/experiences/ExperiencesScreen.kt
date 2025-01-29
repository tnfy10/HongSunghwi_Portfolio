package hongsunghwi.portfolio.feature.experiences

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hongsunghwi.portfolio.core.ui.component.ScrollEndDetectColumn
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@Composable
fun ExperiencesScreen(
    innerPadding: PaddingValues,
    onNavigateToAbout: () -> Unit,
    onNavigateToProjects: () -> Unit,
) {
    ScrollEndDetectColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        onEndScrollBackward = {
            onNavigateToAbout()
        },
        onEndScrollForward = {
            onNavigateToProjects()
        }
    ) {
        Text(
            text = "경력",
            style = PortfolioTheme.typography.displayLarge
        )
    }
}