package com.mahesaiqbal.mvisample.utils

import com.mahesaiqbal.mvisample.data.source.remote.response.movie.MovieResult
import com.mahesaiqbal.mvisample.data.source.remote.response.moviedetail.MovieDetailResponse
import com.mahesaiqbal.mvisample.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataMapper {
    fun mapPopularMovieResultToMovieModel(input: List<MovieResult>): Flow<List<Movie>> = flow {
        emit(
            input.map {
                Movie(
                    id = it.id,
                    backdropPath = it.backdropPath.toString(),
                    originalLanguage = it.originalLanguage,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath.toString(),
                    releaseDate = it.releaseDate,
                    title = it.title,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        )
    }

    fun mapMovieDetailToMovie(
        detail: MovieDetailResponse
    ): Flow<Movie> = flow {
        emit(
            Movie(
                id = detail.id,
                title = detail.title,
                releaseDate = detail.releaseDate,
                backdropPath = detail.backdropPath,
                overview = detail.overview,
                popularity = detail.popularity,
                posterPath = detail.posterPath,
                voteAverage = detail.voteAverage,
                voteCount = detail.voteCount,
                originalLanguage = detail.originalLanguage
            )
        )
    }
}