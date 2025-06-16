package com.mahesaiqbal.mvisample.domain.usecase

import com.mahesaiqbal.mvisample.domain.model.Movie
import com.mahesaiqbal.mvisample.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): Flow<List<Movie>> =
        repository.getPopularMovies()
}