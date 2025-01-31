package hongsunghwi.portfolio.core.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CareerCard(
    modifier: Modifier = Modifier,
    companyName: String,
    startDate: String,
    resignationDay: String? = null,
    companyIntro: String,
    skills: List<String>,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = companyName,
                style = PortfolioTheme.typography.titleLarge
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = "$startDate - ${resignationDay ?: "(재직 중)"}"
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = companyIntro
            )
            Spacer(Modifier.height(10.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                skills.forEach {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(it)
                        }
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            content()
        }
    }
}