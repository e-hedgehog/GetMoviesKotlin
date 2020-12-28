package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehedgehog.android.getmovieskotlin.entities.Movie

class MoviesSearchViewModel : ViewModel() {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>> get() = _listMovies

}