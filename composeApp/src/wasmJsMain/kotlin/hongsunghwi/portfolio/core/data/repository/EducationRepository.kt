package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.Education
import kotlinx.coroutines.flow.Flow

interface EducationRepository {
    fun getEducations(): Flow<List<Education>>
}