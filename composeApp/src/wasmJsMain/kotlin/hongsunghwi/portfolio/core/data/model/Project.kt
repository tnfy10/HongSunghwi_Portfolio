package hongsunghwi.portfolio.core.data.model

import hongsunghwi.portfolio.core.constant.ProjectFilter
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: Int,
    val filter: ProjectFilter,
    val name: String,
    val startDate: String,
    val endDate: String?,
    val skills: List<String>,
    val intro: String,
    val directory: String,
    val showReadme: Boolean,
    val hasImage: Boolean,
    val repo: String,
    val repoUrl: String,
)
