package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehedgehog.android.getmovieskotlin.Application
import com.ehedgehog.android.getmovieskotlin.PaginationHelper
import com.ehedgehog.android.getmovieskotlin.network.MoviesApi
import com.ehedgehog.android.getmovieskotlin.network.MoviesSearchItem
import com.ehedgehog.android.getmovieskotlin.screens.DatabaseManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesSearchViewModel : ViewModel() {

    @Inject
    lateinit var moviesApi: MoviesApi

    @Inject
    lateinit var paginationHelper: PaginationHelper

    @Inject
    lateinit var databaseManager: DatabaseManager

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _listMovies = MutableLiveData<List<MoviesSearchItem>>()
    val listMovies: LiveData<List<MoviesSearchItem>> get() = _listMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        Application.appComponent.injectMoviesSearchViewModel(this)
        _listMovies.value = databaseManager.getStoredMovies()
    }

    fun searchMovies(search: String?, type: String, page: Int) {
        coroutineScope.launch {
            try {
                Log.i("searchScreen", "Request: s=$search, type=$type, page=$page")
                _isLoading.value = true
                val moviesDeferred = moviesApi.searchMovies(search, type, page)
                val searchResult = moviesDeferred.await()
                paginationHelper.totalItems = searchResult.totalResults
                if (paginationHelper.currentPage == 1 || _listMovies.value == null) {
                    databaseManager.clearMovies()
                    _listMovies.value = searchResult.searchResult
                } else
                    _listMovies.value = _listMovies.value?.plus(searchResult.searchResult)

                databaseManager.saveMovies(searchResult.searchResult)
                _isLoading.value = false
            } catch (throwable: Throwable) {
                _isLoading.value = false
                throwable.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}