package hongsunghwi.portfolio.feature.skills

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hongsunghwi.portfolio.core.ui.component.ScrollEndDetectColumn
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@Composable
fun SkillsScreen(
    innerPadding: PaddingValues,
    onNavigateToProjects: () -> Unit,
    onNavigateToEducation: () -> Unit,
) {
    ScrollEndDetectColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        onEndScrollBackward = {
            onNavigateToProjects()
        },
        onEndScrollForward = {
            onNavigateToEducation()
        }
    ) {
        Text(
            text = "Skills",
            style = PortfolioTheme.typography.displayLarge
        )
    }
}