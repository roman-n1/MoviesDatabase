package com.roman.yarullin.moviesdatabase.presentation

import android.util.Log
import com.roman.yarullin.moviesdatabase.MoviesDatabaseApplication
import com.roman.yarullin.moviesdatabase.domain.MyCoroutineDispatcher
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel
import com.roman.yarullin.moviesdatabase.domain.usecase.GetMoviesListUseCase
import com.roman.yarullin.moviesdatabase.navigation.NavManager
import com.roman.yarullin.moviesdatabase.presentation.base.BaseAction
import com.roman.yarullin.moviesdatabase.presentation.base.BaseViewModel
import com.roman.yarullin.moviesdatabase.presentation.base.BaseViewState
import kotlinx.coroutines.*
import javax.inject.Inject

val LOG_TAG: String = MoviesListViewModel::class.java.simpleName
class MoviesListViewModel() : BaseViewModel<MoviesListViewModel.ViewState, MoviesListViewModel.Action>(ViewState(isLoading = true)) {

    private val exceptionHandler: CoroutineExceptionHandler
    private val uiScope: CoroutineScope
    @Inject internal lateinit var getMoviesListUseCase: GetMoviesListUseCase
    @Inject internal lateinit var coroutineDispatcher: MyCoroutineDispatcher
    @Inject internal lateinit var navManager: NavManager

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

            val action = when (result) {
                is GetMoviesListUseCase.Result.Success ->
                    Action.MoviesListLoadingSuccess(result.data)
                is GetMoviesListUseCase.Result.Error ->
                    Action.MoviesListLoadingFailure
            }
            sendAction(action)
        }
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val isError: Event<Boolean> = Event(false),
        val movies: List<MoviesDomainModel> = listOf()
    ): BaseViewState


    sealed class Action : BaseAction {
        class MoviesListLoadingSuccess(val movies: List<MoviesDomainModel>) : Action()
        object MoviesListLoadingFailure : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState {
        return when (viewAction) {
            is Action.MoviesListLoadingSuccess -> ViewState(movies = viewAction.movies)
            is Action.MoviesListLoadingFailure -> state.copy(isLoading = false, isError = Event(true))
        }
    }

    override fun onCleared() {
        super.onCleared()
        MoviesDatabaseApplication.instance.destroyMoviesListComponent()
        uiScope.cancel()
    }
}