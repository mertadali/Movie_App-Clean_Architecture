package com.mertadali.movieappkotlin.data.dependency_injection

import com.mertadali.movieappkotlin.data.remote.MovieApi
import com.mertadali.movieappkotlin.data.repository.MovieRepositoryImpl
import com.mertadali.movieappkotlin.domain.repository.MovieRepository
import com.mertadali.movieappkotlin.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)


    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi) : MovieRepository{
        return MovieRepositoryImpl(api = api)
    }



}