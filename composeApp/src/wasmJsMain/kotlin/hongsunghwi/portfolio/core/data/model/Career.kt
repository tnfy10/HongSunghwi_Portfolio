package hongsunghwi.portfolio.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Career(
    val id: Int,
    val company: String,
    val startDate: String,
    val endDate: String?,
    val intro: String,
    val contents: List<Content>
) {
    @Serializable
    data class Content(
        val id: Int,
        val name: String,
        val startDate: String,
        val endDate: String?,
        val team: String,
        val tech: List<String>,
        val work: String,
        val performance: String?
    )
}