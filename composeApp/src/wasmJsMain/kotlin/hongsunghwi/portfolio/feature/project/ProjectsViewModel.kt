package hongsunghwi.portfolio.feature.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hongsunghwi.portfolio.core.constant.ProjectFilter
import hongsunghwi.portfolio.core.data.repository.ProjectRepository
import hongsunghwi.portfolio.core.ui.state.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProjectsViewModel(
    private val projectRepository: ProjectRepository
) : ViewModel() {
    private val _filterState = MutableStateFlow(ProjectFilter.ALL)
    val filterState = _filterState.asStateFlow()

    val projectState = combine(
        projectRepository.getProjects(),
        _filterState
    ) { projects, filter ->
        projects.filter { if (filter == ProjectFilter.ALL) true else it.filter == filter }
    }.map {
        UiState.Success(it)
    }.stateIn(
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5000),
        scope = viewModelScope
    )

    private val _projectReadmeMap = MutableStateFlow<Map<String, String>>(emptyMap())
    val projectReadmeMap = _projectReadmeMap.asStateFlow()

    private val _projectImageMap = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val projectImageMap = _projectImageMap.asStateFlow()

    fun changeFilter(filter: ProjectFilter) {
        _filterState.value = filter
    }

    fun fetchProjectReadme(directory: String) {
        viewModelScope.launch {
            projectRepository.getProjectReadme(directory).catch {
                println("Failed to fetch project readme: $it")
            }.collectLatest { readme ->
                val copyMap = _projectReadmeMap.value.toMutableMap()
                copyMap[directory] = readme
                _projectReadmeMap.value = copyMap
            }
        }
    }

    fun fetchProjectImage(directory: String, imageCount: Int) {
        viewModelScope.launch {
            projectRepository.getProjectImages(directory, imageCount).catch {
                println("Failed to fetch project image: $it")
            }.collectLatest { imageUris ->
                val copyMap = _projectImageMap.value.toMutableMap()
                copyMap[directory] = imageUris
                _projectImageMap.value = copyMap
            }
        }
    }
}