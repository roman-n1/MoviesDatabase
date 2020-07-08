package com.roman.yarullin.moviesdatabase.di.module

import android.app.Application
import android.content.Context
import com.roman.yarullin.moviesdatabase.domain.MyCoroutineDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideCoroutinesDispatcher(): MyCoroutineDispatcher {
        return MyCoroutineDispatcher()
    }
}