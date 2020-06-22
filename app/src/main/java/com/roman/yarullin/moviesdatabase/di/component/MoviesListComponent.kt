package com.roman.yarullin.moviesdatabase.di.component

import com.roman.yarullin.moviesdatabase.di.module.MoviesListModule
import com.roman.yarullin.moviesdatabase.di.scope.MoviesListScope
import com.roman.yarullin.moviesdatabase.presentation.MoviesListViewModel
import dagger.Component

@MoviesListScope
@Component(dependencies = [MoviesComponent::class], modules = [MoviesListModule::class])
interface MoviesListComponent {
    fun inject(moviesListViewModel: MoviesListViewModel)
}