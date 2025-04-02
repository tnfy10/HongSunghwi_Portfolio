package hongsunghwi.portfolio.feature.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.valentinilk.shimmer.shimmer
import hongsunghwi.portfolio.core.data.model.About
import hongsunghwi.portfolio.core.ui.state.UiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AboutContainer(
    aboutViewModel: AboutViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean
) {
    val aboutState by aboutViewModel.aboutState.collectAsState()

    AboutContainerImpl(
        modifier = modifier,
        isSmallScreen = isSmallScreen,
        aboutState = aboutState
    )
}

@Composable
private fun AboutContainerImpl(
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean,
    aboutState: UiState<About>
) {
    OutlinedCard(
        modifier = modifier
    ) {
        if (aboutState is UiState.Success) {
            val data = aboutState.data

            Column(
                modifier = Modifier.padding(
                    horizontal = if (isSmallScreen) 16.dp else 32.dp,
                    vertical = if (isSmallScreen) 32.dp else 56.dp
                )
            ) {
                SubcomposeAsyncImage(
                    model = data.profileImageUrl,
                    contentDescription = null,
                    loading = {
                        Box(Modifier.fillMaxSize().shimmer()) {
                            Box(Modifier.fillMaxSize().background(Color.LightGray))
                        }
                    },
                    modifier = Modifier
                        .size(if (isSmallScreen) 150.dp else 250.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(32.dp))
                Text(
                    text = data.name,
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Tel : ")
                        withLink(LinkAnnotation.Url("tel:${data.phone}")) {
                            append(data.phone)
                        }
                        append("\n")
                        append("E-mail : ")
                        withLink(LinkAnnotation.Url("mailto:${data.email}")) {
                            append(data.email)
                        }
                    },
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = data.intro,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            Box(
                modifier = modifier.height(if (isSmallScreen) 300.dp else 500.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}