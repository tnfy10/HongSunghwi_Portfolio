package hongsunghwi.portfolio

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import hongsunghwi.portfolio.core.di.repositoryModule
import hongsunghwi.portfolio.core.di.viewModelModule
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import hongsunghwi.portfolio.feature.main.MainScreen
import kotlinx.coroutines.delay
import org.koin.compose.KoinApplication

@Composable
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).apply {
            components {
                add(KtorNetworkFetcherFactory())
            }
            crossfade(true)
        }.build()
    }

    KoinApplication(
        application = {
            modules(repositoryModule, viewModelModule)
        }
    ) {
        var fontLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(500)
            fontLoading = false
        }

        PortfolioTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Crossfade(
                    targetState = fontLoading,
                ) { isLoading ->
                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        MainScreen()
                    }
                }
            }
        }
    }
}