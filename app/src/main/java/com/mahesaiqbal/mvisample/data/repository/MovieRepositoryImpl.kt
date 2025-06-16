package com.mahesaiqbal.mvisample.data.repository

import com.mahesaiqbal.mvisample.data.source.remote.RemoteDataSource
import com.mahesaiqbal.mvisample.data.source.remote.response.movie.MovieResult
import com.mahesaiqbal.mvisample.domain.model.Movie
import com.mahesaiqbal.mvisample.domain.repository.MovieRepository
import com.mahesaiqbal.mvisample.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<List<Movie>> =
        DataMapper.mapPopularMovieResultToMovieModel(remoteDataSource.getPopularMovies().first())

    override suspend fun getMovieDetail(movieId: Int): Flow<Movie> =
        DataMapper.mapMovieDetailToMovie(remoteDataSource.getMovieDetail(movieId).first())
}