package com.roman.yarullin.moviesdatabase.di.component

import android.content.Context
import com.roman.yarullin.moviesdatabase.data.api.ApiServiceInterface
import com.roman.yarullin.moviesdatabase.di.module.AppModule
import com.roman.yarullin.moviesdatabase.di.module.NetworkModule
import com.roman.yarullin.moviesdatabase.domain.MyCoroutineDispatcher
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun apiServiceInterface(): ApiServiceInterface
    fun applicationContext(): Context
    fun coroutineDispatcher(): MyCoroutineDispatcher
}