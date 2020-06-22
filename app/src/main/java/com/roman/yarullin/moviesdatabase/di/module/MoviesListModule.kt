package com.roman.yarullin.moviesdatabase.di.module

import com.roman.yarullin.moviesdatabase.di.scope.MoviesListScope
import com.roman.yarullin.moviesdatabase.domain.repository.MoviesRepository
import com.roman.yarullin.moviesdatabase.domain.usecase.GetMoviesListUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesListModule {
    @Provides
    @MoviesListScope
    fun provideGetMoviesListUseCas(moviesRepository: MoviesRepository) : GetMoviesListUseCase {
        return GetMoviesListUseCase(moviesRepository)
    }
}