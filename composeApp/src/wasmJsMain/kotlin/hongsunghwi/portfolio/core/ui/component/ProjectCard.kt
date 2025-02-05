package hongsunghwi.portfolio.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    name: String,
    filterLabel: String,
    startDate: String,
    endDate: String?,
    intro: String,
    skills: List<String>
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = filterLabel,
                    modifier = Modifier.background(
                        color = Color.Black,
                        shape = RoundedCornerShape(4.dp)
                    ).padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    ),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White
                    )
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "$startDate - ${endDate ?: "(진행 중)"}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = intro,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(20.dp))
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                skills.forEach {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    )
                }
            }
        }
    }
}