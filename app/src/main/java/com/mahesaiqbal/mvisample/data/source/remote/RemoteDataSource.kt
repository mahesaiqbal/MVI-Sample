package com.mahesaiqbal.mvisample.data.source.remote

import com.mahesaiqbal.mvisample.BuildConfig
import com.mahesaiqbal.mvisample.data.source.remote.network.MovieService
import com.mahesaiqbal.mvisample.data.source.remote.response.movie.MovieResult
import com.mahesaiqbal.mvisample.data.source.remote.response.moviedetail.MovieDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {
    fun getPopularMovies(): Flow<List<MovieResult>> =
        flow {
            val response = movieService.getPopularMovies(BuildConfig.API_KEY)
            val data = response.results

            if (data.isNotEmpty()) {
                emit(response.results)
            } else {
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)

    fun getMovieDetail(movieId: Int): Flow<MovieDetailResponse> =
        flow {
            val response = movieService.getMovieDetail(movieId, BuildConfig.API_KEY)
            emit(response)
        }.flowOn(Dispatchers.IO)
}