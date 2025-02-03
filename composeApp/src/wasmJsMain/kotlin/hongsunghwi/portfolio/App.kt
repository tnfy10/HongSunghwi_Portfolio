package hongsunghwi.portfolio

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import hongsunghwi.portfolio.core.di.repositoryModule
import hongsunghwi.portfolio.core.di.viewModelModule
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import hongsunghwi.portfolio.feature.main.MainScreen
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
        PortfolioTheme {
            MainScreen()
        }
    }
}