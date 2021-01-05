package com.ehedgehog.android.getmovieskotlin.network

import com.squareup.moshi.Json
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

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
    val totalSeasons: String?,
    val Response: String
)

data class MoviesResponse(
    @Json(name = "Response")
    val response: String,
    val totalResults: Int,
    @Json(name = "Search")
    val searchResult: List<MoviesSearchItem>
)

open class MoviesSearchItem(
    @PrimaryKey
    @Json(name = "imdbID")
    var id: String? = null,
    var Title: String? = null,
    var Year: String? = null,
    var Type: String? = null,
    @Json(name = "Poster")
    var posterUrl: String? = null
): RealmObject()