package hongsunghwi.portfolio.core.di

import hongsunghwi.portfolio.core.data.repository.CareerRepository
import hongsunghwi.portfolio.core.data.repository.CareerRepositoryImpl
import hongsunghwi.portfolio.core.data.repository.ProjectRepository
import hongsunghwi.portfolio.core.data.repository.ProjectRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CareerRepository> {
        CareerRepositoryImpl()
    }
    single<ProjectRepository> {
        ProjectRepositoryImpl()
    }
}