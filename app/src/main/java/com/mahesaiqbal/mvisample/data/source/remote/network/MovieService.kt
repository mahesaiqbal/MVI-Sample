package com.mahesaiqbal.mvisample.data.source.remote.network

import com.mahesaiqbal.mvisample.data.source.remote.response.movie.MovieResponse
import com.mahesaiqbal.mvisample.data.source.remote.response.moviedetail.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse
}