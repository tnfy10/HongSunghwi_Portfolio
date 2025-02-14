package hongsunghwi.portfolio.feature.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.constant.ProjectFilter
import hongsunghwi.portfolio.core.data.model.Project
import hongsunghwi.portfolio.core.ui.component.ProjectCard
import hongsunghwi.portfolio.core.ui.state.UiState
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectsContainer(
    projectsViewModel: ProjectsViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean,
    columns: Int
) {
    val projectState by projectsViewModel.projectState.collectAsState()
    val selectedFilter by projectsViewModel.filterState.collectAsState()

    Column(
        modifier = modifier
    ) {
        Text(
            text = "프로젝트",
            modifier = Modifier.fillMaxWidth(),
            style = PortfolioTheme.typography.displayMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(60.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(ProjectFilter.entries) {
                FilterChip(
                    selected = it == selectedFilter,
                    onClick = {
                        projectsViewModel.changeFilter(it)
                    },
                    label = {
                        Text(
                            text = it.label,
                            style = PortfolioTheme.typography.labelLarge
                        )
                    }
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (projectState) {
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

                is UiState.Success<List<Project>> -> {
                    val projects = (projectState as UiState.Success<List<Project>>).data

                    if (projects.isEmpty()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if (isSmallScreen) 300.dp else 500.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "현재 진행된 프로젝트가 없습니다.",
                                    style = PortfolioTheme.typography.titleLarge
                                )
                            }
                        }
                    } else {
                        val rows = projects.chunked(columns)

                        for (row in rows) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                for (col in 0..<columns) {
                                    if (col > row.lastIndex) {
                                        Spacer(Modifier.weight(1f).fillMaxHeight())
                                    } else {
                                        val item = row[col]

                                        ProjectCard(
                                            onClick = {
                                                // TODO: 프로젝트 상세
                                            },
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight(),
                                            name = item.name,
                                            filterLabel = item.filter.label,
                                            startDate = item.startDate,
                                            endDate = item.endDate,
                                            intro = item.intro,
                                            skills = item.skills,
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