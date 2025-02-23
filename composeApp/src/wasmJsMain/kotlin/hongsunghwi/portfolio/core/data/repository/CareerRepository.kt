package hongsunghwi.portfolio.core.data.repository

import hongsunghwi.portfolio.core.data.model.Career
import kotlinx.coroutines.flow.Flow

interface CareerRepository {
    fun getCareers(): Flow<List<Career>>
}