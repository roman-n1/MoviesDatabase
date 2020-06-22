package com.roman.yarullin.moviesdatabase.domain.model

data class MoviesDomainModel(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Float,
    val popularity: Float,
    val voteCount: Int,
    val posterPath: String
)