package hongsunghwi.portfolio.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Career(
    val id: Int,
    val company: String,
    val startDate: String,
    val endDate: String?,
    val intro: String,
    val works: List<Work>
)

@Serializable
data class Work(
    val id: Int,
    val name: String,
    val startDate: String,
    val endDate: String?,
    val content: String
)