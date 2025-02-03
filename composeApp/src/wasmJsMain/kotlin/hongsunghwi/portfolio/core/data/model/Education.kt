package hongsunghwi.portfolio.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Education(
    val id: Int,
    val name: String,
    val department: String,
    val degree: String,
    val status: String,
    val admissionDate: String,
    val graduationDate: String
)
