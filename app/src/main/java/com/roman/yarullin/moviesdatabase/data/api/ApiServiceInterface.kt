package com.roman.yarullin.moviesdatabase.data.api

import com.roman.yarullin.moviesdatabase.data.api.response.MovieDiscoverResponse
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("discover/movie")
    suspend fun movieDiscover(): MovieDiscoverResponse
}