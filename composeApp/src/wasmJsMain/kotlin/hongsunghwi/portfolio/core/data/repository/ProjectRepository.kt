package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun getProjects(): Flow<List<Project>>
    fun getProjectReadme(directory: String): Flow<String>
}