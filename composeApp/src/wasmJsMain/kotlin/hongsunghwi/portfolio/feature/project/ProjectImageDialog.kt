package hongsunghwi.portfolio.feature.project

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import hongsunghwi.portfolio.core.constant.Size
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import hongsunghwi_portfolio.composeapp.generated.resources.ic_close_24
import hongsunghwi_portfolio.composeapp.generated.resources.ic_keyboard_arrow_left_24
import hongsunghwi_portfolio.composeapp.generated.resources.ic_keyboard_arrow_right_24
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectImageDialog(
    onDismiss: () -> Unit,
    images: List<String>
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val pagerState = rememberPagerState(pageCount = images::size)
        val scope = rememberCoroutineScope()

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
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    val painter = rememberAsyncImagePainter(images[page])
                    val painterState by painter.state.collectAsState()

                    Crossfade(
                        targetState = painterState,
                        modifier = Modifier.fillMaxSize()
                    ) { state ->
                        when (state) {
                            is AsyncImagePainter.State.Error -> {
                                println("Error: ${state.result}")

                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "이미지를 불러올 수 없습니다."
                                    )
                                }
                            }

                            is AsyncImagePainter.State.Success -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painter,
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }

                            else -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                        enabled = pagerState.currentPage > 0
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_keyboard_arrow_left_24),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "${pagerState.currentPage + 1} / ${pagerState.pageCount}",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFeatureSettings = "tnum"
                        )
                    )
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        enabled = pagerState.currentPage < pagerState.pageCount - 1
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_keyboard_arrow_right_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}