package hongsunghwi.portfolio.core.di

import hongsunghwi.portfolio.core.data.repository.EducationRepository
import hongsunghwi.portfolio.core.data.repository.EducationRepositoryImpl
import hongsunghwi.portfolio.core.data.repository.ProjectRepository
import hongsunghwi.portfolio.core.data.repository.ProjectRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ProjectRepository> {
        ProjectRepositoryImpl()
    }
    single<EducationRepository> {
        EducationRepositoryImpl()
    }
}