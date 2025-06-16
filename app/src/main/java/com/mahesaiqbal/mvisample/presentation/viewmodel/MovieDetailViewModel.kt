package com.mahesaiqbal.mvisample.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.mahesaiqbal.mvisample.domain.usecase.GetMovieDetailUseCase
import com.mahesaiqbal.mvisample.presentation.base.viewmodel.BaseViewModel
import com.mahesaiqbal.mvisample.presentation.view.moviedetail.MovieDetailEffect
import com.mahesaiqbal.mvisample.presentation.view.moviedetail.MovieDetailIntent
import com.mahesaiqbal.mvisample.presentation.view.moviedetail.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel<MovieDetailIntent, MovieDetailState, MovieDetailEffect>() {

    override fun initialState(): MovieDetailState = MovieDetailState()

    override fun onIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.GetMovieDetail -> getMovieDetail(intent.movieId)
            is MovieDetailIntent.NavigateBack -> postEffect {
                MovieDetailEffect.NavigateBack
            }
        }
    }

    private fun getMovieDetail(movieId: Int) {
        updateState { copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            getMovieDetailUseCase(movieId)
                .catch { error ->
                    updateState { copy(isLoading = false, error = error.message) }
                    postEffect {
                        MovieDetailEffect.ShowInfo(
                            error.message ?: "Something went wrong"
                        )
                    }
                }
                .collectLatest { movie ->
                    updateState {
                        copy(isLoading = false, error = null, movieDetail = movie)
                    }
                }
        }
    }
}