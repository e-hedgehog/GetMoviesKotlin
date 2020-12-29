package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehedgehog.android.getmovieskotlin.Application
import com.ehedgehog.android.getmovieskotlin.network.Movie
import com.ehedgehog.android.getmovieskotlin.network.MoviesApi
import com.ehedgehog.android.getmovieskotlin.network.MoviesSearchItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesSearchViewModel : ViewModel() {

    @Inject
    lateinit var moviesApi: MoviesApi

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _listMovies = MutableLiveData<List<MoviesSearchItem>>()
    val listMovies: LiveData<List<MoviesSearchItem>> get() = _listMovies

    init {
        Application.appComponent.injectTasksTrackerViewModel(this)
        searchMovies("spider", "movie", 1)
    }

    private fun searchMovies(search: String, type: String, page: Int) {
        coroutineScope.launch {
            val moviesDeferred = moviesApi.searchMovies(search, type, page)
            val searchResult = moviesDeferred.await()
            Log.i("searchScreen", searchResult.searchResult.toString())
            _listMovies.value = searchResult.searchResult
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}