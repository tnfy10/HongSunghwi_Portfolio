package hongsunghwi.portfolio.core.di

import hongsunghwi.portfolio.feature.education.EducationViewModel
import hongsunghwi.portfolio.feature.project.ProjectsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ProjectsViewModel(get())
    }
    viewModel {
        EducationViewModel(get())
    }
}