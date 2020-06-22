package com.roman.yarullin.moviesdatabase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.yarullin.moviesdatabase.MoviesDatabaseApplication
import com.roman.yarullin.moviesdatabase.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel() : ViewModel() {

    var fragment: MoviesListFragment? = null

    init {
        MoviesDatabaseApplication.instance.moviesListComponent.inject(this)
    }

    @Inject
    internal lateinit var getMoviesListUseCase: GetMoviesListUseCase

    fun getMoviesList() {
        viewModelScope.launch(Dispatchers.Main) {
            val result: GetMoviesListUseCase.Result
            result = getMoviesListUseCase.execute()
            when(result) {
                is GetMoviesListUseCase.Result.Success ->
                    fragment?.showResult(result.data)
                is GetMoviesListUseCase.Result.Error ->
                    fragment?.showError()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        MoviesDatabaseApplication.instance.destroyMoviesListComponent()
    }
}