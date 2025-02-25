package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.Project
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class ProjectRepositoryImpl : ProjectRepository {
    @OptIn(ExperimentalResourceApi::class)
    override fun getProjects(): Flow<List<Project>> = flow {
        val readBytes = Res.readBytes("files/project/project.json")
        val jsonString = readBytes.decodeToString()
        val projects = Json.decodeFromString<List<Project>>(jsonString)
        emit(projects.sortedByDescending { it.id })
    }

    @OptIn(ExperimentalResourceApi::class)
    override fun getProjectReadme(directory: String): Flow<String> = flow {
        val readBytes = Res.readBytes("files/project/$directory/README.md")
        emit(readBytes.decodeToString())
    }
}