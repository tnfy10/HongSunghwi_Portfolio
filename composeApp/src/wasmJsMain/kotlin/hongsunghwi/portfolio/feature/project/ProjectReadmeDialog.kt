package hongsunghwi.portfolio.feature.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.m3.Markdown
import hongsunghwi.portfolio.core.constant.Size
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import hongsunghwi_portfolio.composeapp.generated.resources.ic_close_24
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailDialog(
    onDismiss: () -> Unit,
    content: String?
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier.widthIn(max = Size.BASE_SCREEN_WIDTH)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopAppBar(
                    title = {},
                    actions = {
                        IconButton(
                            onClick = onDismiss
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_close_24),
                                contentDescription = "닫기"
                            )
                        }
                    }
                )
                if (content != null) {
                    Markdown(
                        content = content,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(16.dp),
                        imageTransformer = Coil3ImageTransformerImpl
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}