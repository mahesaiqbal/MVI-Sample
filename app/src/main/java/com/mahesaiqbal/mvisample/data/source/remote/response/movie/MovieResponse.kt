package com.mahesaiqbal.mvisample.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var results: List<MovieResult>
)