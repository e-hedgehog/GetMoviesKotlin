package com.ehedgehog.android.getmovieskotlin.screens

import com.ehedgehog.android.getmovieskotlin.network.MoviesSearchItem
import io.realm.Realm

class DatabaseManager(private val realm: Realm) {

    fun getStoredMovies(): List<MoviesSearchItem> {
        val moviesList = realm.where(MoviesSearchItem::class.java).findAll()
        return realm.copyFromRealm(moviesList)
    }

    fun saveMovies(moviesList: List<MoviesSearchItem>) {
        realm.executeTransaction {
            it.insert(moviesList)
        }
    }

    fun clearMovies() {
        realm.executeTransaction {
            it.delete(MoviesSearchItem::class.java)
        }
    }

}