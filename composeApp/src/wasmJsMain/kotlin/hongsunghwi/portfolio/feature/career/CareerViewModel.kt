package hongsunghwi.portfolio.feature.career

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hongsunghwi.portfolio.core.data.repository.CareerRepository
import hongsunghwi.portfolio.core.ui.state.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CareerViewModel(
    careerRepository: CareerRepository
) : ViewModel() {
    val careerState = careerRepository.getCareers().map {
        UiState.Success(it)
    }.stateIn(
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5000),
        scope = viewModelScope
    )
}