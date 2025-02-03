package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.Education
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class EducationRepositoryImpl : EducationRepository {
    @OptIn(ExperimentalResourceApi::class)
    override fun getEducations(): Flow<List<Education>> = flow {
        val readBytes = Res.readBytes("files/education.json")
        val jsonString = readBytes.decodeToString()
        val educations = Json.decodeFromString<List<Education>>(jsonString)
        emit(educations.sortedByDescending { it.id })
    }
}