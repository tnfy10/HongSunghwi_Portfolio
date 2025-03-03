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
    override fun getProjectImages(directory: String, imageCount: Int): Flow<List<String>> = flow {
        val uris = buildList {
            repeat(imageCount) {
                add(Res.getUri("files/project/$directory/images/${it + 1}.jpg"))
            }
        }
        emit(uris)
    }
}