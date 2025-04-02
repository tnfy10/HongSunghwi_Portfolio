package hongsunghwi.portfolio.feature.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hongsunghwi.portfolio.core.data.repository.AboutRepository
import hongsunghwi.portfolio.core.ui.state.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AboutViewModel(
    aboutRepository: AboutRepository
) : ViewModel() {
    val aboutState = aboutRepository.getAbout().map {
        UiState.Success(it)
    }.stateIn(
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5000),
        scope = viewModelScope
    )
}