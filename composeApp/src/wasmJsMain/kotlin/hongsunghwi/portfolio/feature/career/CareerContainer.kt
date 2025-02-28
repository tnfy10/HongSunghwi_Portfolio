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
                                career.contents.forEach { content ->
                                    Column {
                                        Text(
                                            text = content.name,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = "${content.startDate} - ${content.endDate ?: "진행 중"}",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Spacer(Modifier.height(8.dp))
                                        Text(
                                            text = "팀 구성",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Text(
                                            text = content.team,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(Modifier.height(8.dp))
                                        Text(
                                            text = "사용 기술",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Text(
                                            text = content.tech.joinToString(", "),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Spacer(Modifier.height(8.dp))
                                        Text(
                                            text = "주요 업무",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Text(
                                            text = content.work,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        content.performance?.let {
                                            Spacer(Modifier.height(8.dp))
                                            Text(
                                                text = "주요 성과 및 문제해결",
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                            Text(
                                                text = it,
                                                style = MaterialTheme.typography.bodyMedium
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
}