package hongsunghwi.portfolio

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import hongsunghwi.portfolio.view.MainScreen

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

    PortfolioTheme {
        MainScreen()
    }
}