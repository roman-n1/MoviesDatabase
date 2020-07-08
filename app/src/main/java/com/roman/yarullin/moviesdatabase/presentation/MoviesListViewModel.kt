package com.roman.yarullin.moviesdatabase.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.roman.yarullin.moviesdatabase.MoviesDatabaseApplication
import com.roman.yarullin.moviesdatabase.domain.MyCoroutineDispatcher
import com.roman.yarullin.moviesdatabase.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

val LOG_TAG: String = MoviesListViewModel::class.java.simpleName
class MoviesListViewModel() : ViewModel() {

    var fragment: MoviesListFragment? = null
    private val exceptionHandler: CoroutineExceptionHandler
    private val uiScope: CoroutineScope

    @Inject
    internal lateinit var getMoviesListUseCase: GetMoviesListUseCase

    @Inject
    internal lateinit var coroutineDispatcher: MyCoroutineDispatcher

    init {
        MoviesDatabaseApplication.instance.moviesListComponent.inject(this)
        exceptionHandler = CoroutineExceptionHandler { context, exception ->
            Log.e(LOG_TAG, "Caught $exception")
        }

        uiScope = CoroutineScope(coroutineDispatcher.Main + SupervisorJob() + exceptionHandler)
    }

    fun getMoviesList() {
        uiScope.launch() {
            val result: GetMoviesListUseCase.Result
            result = getMoviesListUseCase.execute()

            when (result) {
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
        uiScope.cancel()
    }
}