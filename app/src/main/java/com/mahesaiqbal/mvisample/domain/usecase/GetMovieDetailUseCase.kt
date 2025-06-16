package com.mahesaiqbal.mvisample.domain.usecase

import com.mahesaiqbal.mvisample.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) =
        repository.getMovieDetail(movieId)
}