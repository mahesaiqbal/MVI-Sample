package com.mahesaiqbal.mvisample.di

import com.mahesaiqbal.mvisample.data.repository.MovieRepositoryImpl
import com.mahesaiqbal.mvisample.data.source.remote.RemoteDataSource
import com.mahesaiqbal.mvisample.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): MovieRepository =
        MovieRepositoryImpl(remoteDataSource)

}