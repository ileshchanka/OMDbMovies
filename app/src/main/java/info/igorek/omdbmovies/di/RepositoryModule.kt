package info.igorek.omdbmovies.di

import dagger.Binds
import dagger.Module
import info.igorek.omdbmovies.api.repository.MoviesRepository
import info.igorek.omdbmovies.api.repository.MoviesRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindTablesRepository(tablesRepository: MoviesRepositoryImpl): MoviesRepository
}
