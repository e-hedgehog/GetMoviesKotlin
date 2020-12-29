package com.ehedgehog.android.getmovieskotlin.network

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "imdbID")
    val id: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language: String,
    val Country: String,
    val Awards: String,
    @Json(name = "Poster")
    val posterUrl: String,
    val imdbRating: String,
    val imdbVotes: String,
    val totalSeasons: String,
    val Response: String
)

data class MoviesResponse(
    @Json(name = "Response")
    val response: String,
    val totalResults: Int,
    @Json(name = "Search")
    val searchResult: List<MoviesSearchItem>
)

data class MoviesSearchItem(
    @Json(name = "imdbID")
    val id: String,
    val Title: String,
    val Year: String,
    val Type: String,
    @Json(name = "Poster")
    val posterUrl: String
)