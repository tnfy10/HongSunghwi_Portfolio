package hongsunghwi.portfolio.feature.career

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.data.model.Career
import hongsunghwi.portfolio.core.ui.component.CareerCard
import hongsunghwi.portfolio.core.ui.state.UiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CareerContainer(
    careerViewModel: CareerViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean
) {
    val careerState by careerViewModel.careerState.collectAsState()

    Column(
        modifier = modifier,
    ) {
        Text(
            text = "경력",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.displayMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(60.dp))
        when (careerState) {
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

            is UiState.Success<List<Career>> -> {
                val careers = (careerState as UiState.Success<List<Career>>).data

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    careers.forEach { career ->
                        CareerCard(
                            modifier = Modifier.fillMaxWidth(),
                            companyName = career.company,
                            startDate = career.startDate,
                            endDate = career.endDate,
                            companyIntro = career.intro
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                career.works.forEach { work ->
                                    Column {
                                        Text(
                                            text = work.name,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = "${work.startDate} - ${work.endDate ?: "진행 중"}",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Spacer(Modifier.height(8.dp))
                                        Text(
                                            text = work.content
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
}