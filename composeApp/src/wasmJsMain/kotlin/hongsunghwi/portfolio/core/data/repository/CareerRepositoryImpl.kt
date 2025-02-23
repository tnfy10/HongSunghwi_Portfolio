package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.Career
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class CareerRepositoryImpl : CareerRepository {
    @OptIn(ExperimentalResourceApi::class)
    override fun getCareers(): Flow<List<Career>> = flow {
        val readBytes = Res.readBytes("files/career.json")
        val jsonString = readBytes.decodeToString()
        val careers = Json.decodeFromString<List<Career>>(jsonString).map { career ->
            career.copy(
                works = career.works.sortedByDescending { it.id }
            )
        }
        emit(careers.sortedByDescending { it.id })
    }
}