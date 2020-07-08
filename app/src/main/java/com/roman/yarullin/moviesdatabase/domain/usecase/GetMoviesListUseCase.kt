package com.roman.yarullin.moviesdatabase.domain.usecase

import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel
import com.roman.yarullin.moviesdatabase.domain.repository.MoviesRepository

class GetMoviesListUseCase(
    private val repository: MoviesRepository
) {
    sealed class Result {
        data class Success(val data: List<MoviesDomainModel>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(): Result {
        return try {
            Result.Success(repository.getPopularMovies())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}