package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.About
import kotlinx.coroutines.flow.Flow

interface AboutRepository {
    fun getAbout(): Flow<About>
}