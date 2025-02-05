package hongsunghwi.portfolio.feature.skill

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsContainer(
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Skills",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.displayMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(60.dp))
        FlowRow(
            modifier = Modifier.heightIn(min = if (isSmallScreen) 100.dp else 200.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val skills = listOf("Android", "Android Jetpack", "Kotlin", "Java", "Flutter", "Dart", "Firebase")
            skills.forEach {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                )
            }
        }
    }
}