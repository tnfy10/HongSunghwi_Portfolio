package hongsunghwi.portfolio.feature.projects

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hongsunghwi.portfolio.core.ui.component.ScrollEndDetectColumn
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@Composable
fun ProjectsScreen(
    innerPadding: PaddingValues,
    onNavigateToExperience: () -> Unit,
    onNavigateToSkills: () -> Unit,
) {
    ScrollEndDetectColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        onEndScrollBackward = {
            onNavigateToExperience()
        },
        onEndScrollForward = {
            onNavigateToSkills()
        }
    ) {
        Text(
            text = "프로젝트",
            style = PortfolioTheme.typography.displayLarge
        )
    }
}