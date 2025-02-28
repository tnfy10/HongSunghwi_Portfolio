package hongsunghwi.portfolio.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CareerCard(
    modifier: Modifier = Modifier,
    companyName: String,
    startDate: String,
    endDate: String? = null,
    companyIntro: String,
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
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "$startDate - ${endDate ?: "재직 중"}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = companyIntro,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(32.dp))
            content()
        }
    }
}