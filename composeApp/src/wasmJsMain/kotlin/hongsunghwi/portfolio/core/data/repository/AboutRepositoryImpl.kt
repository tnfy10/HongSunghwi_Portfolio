package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.About
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class AboutRepositoryImpl : AboutRepository {
    @OptIn(ExperimentalResourceApi::class)
    override fun getAbout(): Flow<About> = flow {
        val readBytes = Res.readBytes("files/about.json")
        val jsonString = readBytes.decodeToString()
        val about = Json.decodeFromString<About>(jsonString)
        emit(about)
    }
}