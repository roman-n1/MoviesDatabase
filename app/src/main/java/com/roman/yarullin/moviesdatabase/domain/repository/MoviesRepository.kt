package com.roman.yarullin.moviesdatabase.domain.repository

import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel

interface MoviesRepository {
    suspend fun getPopularMovies(): List<MoviesDomainModel>
}