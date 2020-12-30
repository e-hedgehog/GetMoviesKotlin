package com.ehedgehog.android.getmovieskotlin.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/")
    fun searchMovies(
        @Query("s") s: String?,
        @Query("type") type: String,
        @Query("page") page: Int
    ): Deferred<MoviesResponse>

    @GET("/")
    fun getMovie(@Query("i") id: String): Deferred<Movie>

}