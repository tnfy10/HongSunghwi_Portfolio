package hongsunghwi.portfolio.feature.education

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.data.model.Education
import hongsunghwi.portfolio.core.ui.state.UiState
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EducationContainer(
    educationViewModel: EducationViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean
) {
    val educationState by educationViewModel.educationState.collectAsState()

    Column(
        modifier = modifier
    ) {
        Text(
            text = "학력",
            modifier = Modifier.fillMaxWidth(),
            style = PortfolioTheme.typography.displayMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(60.dp))
        when (educationState) {
            is UiState.Error -> {}
            UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isSmallScreen) 300.dp else 500.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success<List<Education>> -> {
                val items = (educationState as UiState.Success<List<Education>>).data

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = if (isSmallScreen) 300.dp else 500.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items.forEach { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min),
                                horizontalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                Text(
                                    text = "${item.admissionDate} - ${item.graduationDate}",
                                    style = PortfolioTheme.typography.titleSmall
                                )
                                VerticalDivider(
                                    modifier = Modifier.fillMaxHeight()
                                )
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = item.name,
                                        style = PortfolioTheme.typography.titleLarge
                                    )
                                    Text(
                                        text = "${item.department} ${item.degree}"
                                    )
                                    Spacer(Modifier.height(28.dp))
                                    Text(
                                        text = item.status,
                                        style = PortfolioTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}