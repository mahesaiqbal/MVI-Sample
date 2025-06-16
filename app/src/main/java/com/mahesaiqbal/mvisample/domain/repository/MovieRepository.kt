package com.mahesaiqbal.mvisample.domain.repository

import com.mahesaiqbal.mvisample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): Flow<Movie>
}