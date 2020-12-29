package com.ehedgehog.android.getmovieskotlin.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehedgehog.android.getmovieskotlin.Application
import com.ehedgehog.android.getmovieskotlin.network.Movie
import com.ehedgehog.android.getmovieskotlin.network.MoviesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel : ViewModel() {

    @Inject
    lateinit var moviesApi: MoviesApi

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _movieId = MutableLiveData<String>()
    val movieId: LiveData<String> get() = _movieId

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    init {
        Application.appComponent.injectMovieDetailsViewModel(this)
    }

    fun getMovieById(movieId: String) {
        coroutineScope.launch {
            try {
                val movieDeferred = moviesApi.getMovie(movieId)
                _movie.value = movieDeferred.await()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

    fun initMovie(movieId: String) {
        _movieId.value = movieId
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}