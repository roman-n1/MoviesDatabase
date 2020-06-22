package com.roman.yarullin.moviesdatabase.data.api.response

import com.google.gson.annotations.SerializedName
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel

data class MovieDataModel(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Float,
    val popularity: Float,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("poster_path") val posterPath: String
    )

fun MovieDataModel.toDomainModel(): MoviesDomainModel {
    return MoviesDomainModel(
        id = id,
        title = title,
        overview = overview,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        posterPath = posterPath
    )
}