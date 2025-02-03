package hongsunghwi.portfolio.feature.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hongsunghwi.portfolio.core.data.repository.EducationRepository
import hongsunghwi.portfolio.core.ui.state.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EducationViewModel(
    educationRepository: EducationRepository
): ViewModel() {
    val educationState = educationRepository.getEducations().map {
        UiState.Success(it)
    }.stateIn(
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5000),
        scope = viewModelScope
    )
}