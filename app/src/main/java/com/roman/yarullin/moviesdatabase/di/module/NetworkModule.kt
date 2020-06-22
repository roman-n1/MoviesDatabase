package com.roman.yarullin.moviesdatabase.di.module

import com.roman.yarullin.moviesdatabase.BuildConfig
import com.roman.yarullin.moviesdatabase.data.api.ApiServiceInterface
import com.roman.yarullin.moviesdatabase.data.api.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

internal const val API_BASE_URL_NAME = "apiBaseUrl"
internal const val API_KEY_NAME = "apiKey"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(@Named(API_BASE_URL_NAME) baseUrl: String, okHttpClient: OkHttpClient) : ApiServiceInterface {
        val retrofit = retrofit2.Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    @Named(API_BASE_URL_NAME)
    fun provideBaseApiURL(): String = BuildConfig.API_BASE_URL

    @Provides
    @Named(API_KEY_NAME)
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named(API_KEY_NAME) apiKey: String): OkHttpClient {
        return with(OkHttpClient().newBuilder()) {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(interceptor)
            }
            addInterceptor(AuthenticationInterceptor(apiKey))
        }.build()
    }
}