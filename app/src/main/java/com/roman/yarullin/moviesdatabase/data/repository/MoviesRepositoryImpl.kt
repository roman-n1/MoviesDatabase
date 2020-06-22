package com.roman.yarullin.moviesdatabase.data.repository

import com.roman.yarullin.moviesdatabase.data.api.ApiServiceInterface
import com.roman.yarullin.moviesdatabase.data.api.response.toDomainModel
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel
import com.roman.yarullin.moviesdatabase.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val apiServiceInterface: ApiServiceInterface): MoviesRepository {
    override suspend fun getPopularMovies(): List<MoviesDomainModel> =
        apiServiceInterface
            .movieDiscover()
            .results
            .map { it.toDomainModel() }
}