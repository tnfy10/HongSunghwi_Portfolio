package hongsunghwi.portfolio.core.di

import hongsunghwi.portfolio.core.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<AboutRepository> {
        AboutRepositoryImpl()
    }
    single<CareerRepository> {
        CareerRepositoryImpl()
    }
    single<ProjectRepository> {
        ProjectRepositoryImpl()
    }
}