package com.roman.yarullin.moviesdatabase.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roman.yarullin.moviesdatabase.MoviesDatabaseApplication
import com.roman.yarullin.moviesdatabase.domain.MyCoroutineDispatcher
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel
import com.roman.yarullin.moviesdatabase.domain.usecase.GetMoviesListUseCase
import com.roman.yarullin.moviesdatabase.navigation.NavManager
import kotlinx.coroutines.*
import javax.inject.Inject

val LOG_TAG: String = MoviesListViewModel::class.java.simpleName
class MoviesListViewModel() : ViewModel() {

    private val exceptionHandler: CoroutineExceptionHandler
    private val uiScope: CoroutineScope
    @Inject internal lateinit var getMoviesListUseCase: GetMoviesListUseCase
    @Inject internal lateinit var coroutineDispatcher: MyCoroutineDispatcher
    @Inject internal lateinit var navManager: NavManager
    private val stateMutableLiveData = MutableLiveData<ViewState>(ViewState(isLoading = true))
    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    init {
        MoviesDatabaseApplication.instance.moviesListComponent.inject(this)
        exceptionHandler = CoroutineExceptionHandler { context, exception ->
            Log.e(LOG_TAG, "Caught $exception")
        }

        uiScope = CoroutineScope(coroutineDispatcher.Main + SupervisorJob() + exceptionHandler)
    }

    fun getMoviesList() {
        uiScope.launch() {
            val result: GetMoviesListUseCase.Result = getMoviesListUseCase.execute()

            when (result) {
                is GetMoviesListUseCase.Result.Success ->
                    stateMutableLiveData.value = ViewState(isLoading = false, isError = Event(false), albums = result.data)
                is GetMoviesListUseCase.Result.Error ->
                    stateMutableLiveData.value = ViewState(isLoading = false, isError = Event(true), albums = emptyList())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        MoviesDatabaseApplication.instance.destroyMoviesListComponent()
        uiScope.cancel()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val isError: Event<Boolean> = Event(false),
        val albums: List<MoviesDomainModel> = listOf()
    )
}