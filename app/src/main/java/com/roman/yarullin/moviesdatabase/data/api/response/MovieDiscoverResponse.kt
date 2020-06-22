package com.roman.yarullin.moviesdatabase.data.api.response

data class MovieDiscoverResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieDataModel>
)