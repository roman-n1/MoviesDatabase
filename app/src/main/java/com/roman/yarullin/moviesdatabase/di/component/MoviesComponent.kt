package com.roman.yarullin.moviesdatabase.di.component

import com.roman.yarullin.moviesdatabase.di.module.MoviesModule
import com.roman.yarullin.moviesdatabase.di.scope.MoviesScope
import com.roman.yarullin.moviesdatabase.domain.repository.MoviesRepository
import dagger.Component

@MoviesScope
@Component(dependencies = [AppComponent::class], modules = [MoviesModule::class])
interface MoviesComponent {
    fun moviesRepository(): MoviesRepository
}