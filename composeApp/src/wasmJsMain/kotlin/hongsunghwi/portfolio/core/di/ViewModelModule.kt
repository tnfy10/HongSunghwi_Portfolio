package hongsunghwi.portfolio.core.di

import hongsunghwi.portfolio.feature.career.CareerViewModel
import hongsunghwi.portfolio.feature.project.ProjectsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CareerViewModel(get())
    }
    viewModel {
        ProjectsViewModel(get())
    }
}