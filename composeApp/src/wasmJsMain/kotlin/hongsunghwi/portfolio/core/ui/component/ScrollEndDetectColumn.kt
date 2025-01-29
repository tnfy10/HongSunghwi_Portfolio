package hongsunghwi.portfolio.core.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hongsunghwi.portfolio.core.ui.component.ScrollDirection.Backward
import hongsunghwi.portfolio.core.ui.component.ScrollDirection.Forward

enum class ScrollDirection {
    Backward, Forward
}

@Composable
fun ScrollEndDetectColumn(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onEndScrollBackward: () -> Unit = {},
    onEndScrollForward: () -> Unit = {},
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    var scrollDirection: ScrollDirection? by remember { mutableStateOf(null) }

    LaunchedEffect(scrollDirection) {
        when {
            scrollDirection == Backward && !scrollState.canScrollBackward -> {
                onEndScrollBackward()
            }

            scrollDirection == Forward && !scrollState.canScrollForward -> {
                onEndScrollForward()
            }
        }
    }

    Column(
        modifier = Modifier
            .scrollable(
                state = rememberScrollableState { delta ->
                    scrollDirection = when {
                        delta >= 20 -> Backward
                        delta <= -20 -> Forward
                        else -> null
                    }
                    delta
                },
                orientation = Orientation.Vertical
            ).then(modifier)
            .verticalScroll(scrollState),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}