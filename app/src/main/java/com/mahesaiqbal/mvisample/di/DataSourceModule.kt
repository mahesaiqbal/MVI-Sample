package com.mahesaiqbal.mvisample.di

import com.mahesaiqbal.mvisample.data.source.remote.RemoteDataSource
import com.mahesaiqbal.mvisample.data.source.remote.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(movieService: MovieService): RemoteDataSource =
        RemoteDataSource(movieService)
}