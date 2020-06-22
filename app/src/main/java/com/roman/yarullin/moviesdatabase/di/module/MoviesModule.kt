package com.roman.yarullin.moviesdatabase.di.module

import com.roman.yarullin.moviesdatabase.data.api.ApiServiceInterface
import com.roman.yarullin.moviesdatabase.data.repository.MoviesRepositoryImpl
import com.roman.yarullin.moviesdatabase.di.scope.MoviesScope
import com.roman.yarullin.moviesdatabase.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {
    @Provides
    @MoviesScope
    fun provideMoviesRepository(apiServiceInterface: ApiServiceInterface) : MoviesRepository {
        return MoviesRepositoryImpl(apiServiceInterface)
    }
}