package hongsunghwi.portfolio.core.ui.state

sealed class UiState<out T : Any> {
    data object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val throwable: Throwable? = null) : UiState<Nothing>()
}