package com.mahesaiqbal.mvisample.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.mahesaiqbal.mvisample.domain.usecase.GetPopularMoviesUseCase
import com.mahesaiqbal.mvisample.presentation.base.viewmodel.BaseViewModel
import com.mahesaiqbal.mvisample.presentation.view.popularmovie.PopularMovieEffect
import com.mahesaiqbal.mvisample.presentation.view.popularmovie.PopularMovieIntent
import com.mahesaiqbal.mvisample.presentation.view.popularmovie.PopularMovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel<PopularMovieIntent, PopularMovieState, PopularMovieEffect>() {

    override fun initialState(): PopularMovieState = PopularMovieState()

    override fun onIntent(intent: PopularMovieIntent) {
        when (intent) {
            is PopularMovieIntent.GetPopularMovies -> getPopularMovies()
            is PopularMovieIntent.NavigateToDetail -> postEffect {
                PopularMovieEffect.NavigateToDetail(intent.movieId)
            }
        }
    }

    private fun getPopularMovies() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMoviesUseCase()
                .catch { error ->
                    updateState {
                        copy(isLoading = false, error = error.message, movies = emptyList())
                    }
                    postEffect {
                        PopularMovieEffect.ShowInfo(error.message ?: "Something went wrong")
                    }
                }
                .collectLatest { movies ->
                    updateState {
                        copy(isLoading = false, error = null, movies = movies)
                    }
                }
        }
    }
}