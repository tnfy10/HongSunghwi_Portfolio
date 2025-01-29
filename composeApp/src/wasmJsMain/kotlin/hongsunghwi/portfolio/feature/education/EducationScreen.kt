package hongsunghwi.portfolio.feature.education

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hongsunghwi.portfolio.core.ui.component.ScrollEndDetectColumn
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@Composable
fun EducationScreen(
    innerPadding: PaddingValues,
    onNavigateToSkills: () -> Unit,
) {
    ScrollEndDetectColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        onEndScrollBackward = {
            onNavigateToSkills()
        }
    ) {
        Text(
            text = "학력",
            style = PortfolioTheme.typography.displayLarge
        )
    }
}