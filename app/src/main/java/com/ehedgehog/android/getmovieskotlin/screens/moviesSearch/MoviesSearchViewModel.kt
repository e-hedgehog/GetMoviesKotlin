package com.ehedgehog.android.getmovieskotlin.screens.moviesSearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehedgehog.android.getmovieskotlin.Application
import com.ehedgehog.android.getmovieskotlin.PaginationHelper
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

    @Inject
    lateinit var paginationHelper: PaginationHelper

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _listMovies = MutableLiveData<List<MoviesSearchItem>>()
    val listMovies: LiveData<List<MoviesSearchItem>> get() = _listMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        Application.appComponent.injectMoviesSearchViewModel(this)
    }

    fun searchMovies(search: String?, type: String, page: Int) {
        coroutineScope.launch {
            try {
                Log.i("searchScreen", "Request: s=$search, type=$type, page=$page")
                _isLoading.value = true
                val moviesDeferred = moviesApi.searchMovies(search, type, page)
                val searchResult = moviesDeferred.await()
                paginationHelper.totalItems = searchResult.totalResults
                if (paginationHelper.currentPage == 1 || _listMovies.value == null)
                    _listMovies.value = searchResult.searchResult
                else
                    _listMovies.value = _listMovies.value?.plus(searchResult.searchResult)

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