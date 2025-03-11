package hongsunghwi.portfolio.feature.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AboutContainer(
    modifier: Modifier = Modifier,
    isSmallScreen: Boolean
) {
    OutlinedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = if (isSmallScreen) 16.dp else 32.dp,
                vertical = if (isSmallScreen) 32.dp else 56.dp
            )
        ) {
            AsyncImage(
                model = "https://avatars.githubusercontent.com/u/62270548?v=4",
                contentDescription = null,
                modifier = Modifier
                    .size(if (isSmallScreen) 150.dp else 250.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = "홍성휘",
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    append("Tel : ")
                    withLink(LinkAnnotation.Url("tel:010-5270-5964")) {
                        append("010-5270-5964")
                    }
                    append("\n")
                    append("E-mail : ")
                    withLink(LinkAnnotation.Url("mailto:tnfy10@gmail.com")) {
                        append("tnfy10@gmail.com")
                    }
                },
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = buildAnnotatedString {
                    append("Android SDK, Jetpack을 이용한 안드로이드 앱 개발과 Flutter를 이용한 크로스 플랫폼 앱 개발, 구글 플레이 배포 및 구글 인앱결제 구현 경험이 있습니다.")
                    append("\n")
                    append("새로운 기술을 배워 적용하는 것을 좋아하며, 새로운 기술이나 찾아본 기술이 좋다고 생각하면 동료와 공유하여 검토하고 적용하고 있습니다.")
                    append("\n")
                    append("사용자 친화적인 앱 개발을 지향합니다.")
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}