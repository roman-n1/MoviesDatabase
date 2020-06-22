package com.roman.yarullin.moviesdatabase

import android.app.Application
import com.roman.yarullin.moviesdatabase.di.component.*
import com.roman.yarullin.moviesdatabase.di.module.AppModule

class MoviesDatabaseApplication : Application() {

    companion object {
        lateinit var instance: MoviesDatabaseApplication
    }

    private lateinit var _appComponent: AppComponent

    val appComponent: AppComponent
        get() = _appComponent

    private var _moviesComponent: MoviesComponent? = null

    val moviesComponent: MoviesComponent
        get() {
            if (_moviesComponent == null) {
                _moviesComponent = DaggerMoviesComponent.builder().appComponent(_appComponent).build()
            }
            return _moviesComponent!!
        }

    fun destroyMoviesComponent() {
        _moviesComponent = null
    }

    private var _moviesListComponent: MoviesListComponent? = null

    val moviesListComponent: MoviesListComponent
        get() {
            if (_moviesListComponent == null) {
                _moviesListComponent = DaggerMoviesListComponent.builder().moviesComponent(moviesComponent).build()

            }
            return _moviesListComponent!!
        }

    fun destroyMoviesListComponent() {
        _moviesListComponent = null
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        createAppComponent(this)
    }

    private fun createAppComponent(application: Application) {
        _appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }
}