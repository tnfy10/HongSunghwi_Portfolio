package hongsunghwi.portfolio.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import hongsunghwi_portfolio.composeapp.generated.resources.ic_image_24
import hongsunghwi_portfolio.composeapp.generated.resources.ic_two_pager_24
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(
    onClickRepo: () -> Unit,
    onClickReadme: () -> Unit,
    onClickImages: () -> Unit,
    modifier: Modifier = Modifier,
    name: String,
    filterLabel: String,
    startDate: String,
    endDate: String?,
    intro: String,
    skills: List<String>,
    showReadme: Boolean,
    repo: String?,
    showImageButton: Boolean
) {
    Card(
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
                    text = "$startDate - ${endDate ?: "진행 중"}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = intro,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "개발환경 및 사용기술",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = skills.joinToString(", "),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (showReadme) {
                    Button(
                        onClick = onClickReadme
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_two_pager_24),
                            contentDescription = "자세히 보기"
                        )
                        Spacer(Modifier.widthIn(4.dp))
                        Text(
                            text = "자세히 보기"
                        )
                    }
                }
                if (showImageButton) {
                    Button(
                        onClick = onClickImages
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_image_24),
                            contentDescription = "이미지"
                        )
                        Spacer(Modifier.widthIn(4.dp))
                        Text(
                            text = "이미지"
                        )
                    }
                }
                if (repo != null) {
                    Button(
                        onClick = onClickRepo
                    ) {
                        Text(
                            text = repo
                        )
                    }
                }
            }
        }
    }
}