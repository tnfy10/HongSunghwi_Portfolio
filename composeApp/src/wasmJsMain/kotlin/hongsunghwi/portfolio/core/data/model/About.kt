package hongsunghwi.portfolio.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class About(
    val name: String,
    val phone: String,
    val email: String,
    val profileImageUrl: String,
    val intro: String
)
