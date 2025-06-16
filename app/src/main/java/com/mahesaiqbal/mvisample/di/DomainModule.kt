package com.mahesaiqbal.mvisample.di

import com.mahesaiqbal.mvisample.domain.repository.MovieRepository
import com.mahesaiqbal.mvisample.domain.usecase.GetMovieDetailUseCase
import com.mahesaiqbal.mvisample.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideUseCase(repository: MovieRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repository)

    @Singleton
    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository): GetMovieDetailUseCase =
        GetMovieDetailUseCase(repository)
}