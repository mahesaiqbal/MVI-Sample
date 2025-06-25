package com.mahesaiqbal.mvisample.presentation.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mahesaiqbal.mvisample.R
import com.mahesaiqbal.mvisample.presentation.ui.components.BottomNavigationBar
import com.mahesaiqbal.mvisample.presentation.ui.components.CenterTopAppBar
import com.mahesaiqbal.mvisample.presentation.ui.navigation.BottomBarScreen
import com.mahesaiqbal.mvisample.presentation.ui.navigation.BottomBarScreen.NowPlaying
import com.mahesaiqbal.mvisample.presentation.ui.navigation.BottomBarScreen.Popular
import com.mahesaiqbal.mvisample.presentation.ui.navigation.BottomBarScreen.Upcoming
import com.mahesaiqbal.mvisample.presentation.ui.navigation.BottomBarScreenSaver
import com.mahesaiqbal.mvisample.presentation.ui.navigation.bottomBarItems
import com.mahesaiqbal.mvisample.presentation.view.popularmovie.PopularMovieScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    onNavigateToDetail: (Int) -> Unit = {},

) {
    val context = LocalContext.current
    val backStack = rememberNavBackStack<BottomBarScreen>(Popular)

    var currentBottomBarScreen: BottomBarScreen by rememberSaveable(
        stateSaver = BottomBarScreenSaver
    ) { mutableStateOf(Popular) }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = { CenterTopAppBar(title = context.getString(R.string.popular_movies_title_bar)) },
        bottomBar = {
            BottomNavigationBar(
                currentBottomBarScreen = currentBottomBarScreen,
            ) { destination ->
                if (backStack.lastOrNull() != destination) {
                    if (backStack.lastOrNull() in bottomBarItems) {
                        backStack.removeAt(backStack.lastIndex)
                    }
                    backStack.add(destination)
                    currentBottomBarScreen = destination
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Popular> {
                    PopularMovieScreen(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        onShowInfo = { message ->
                            scope.launch { snackbarHostState.showSnackbar(message) }
                        },
                        onNavigateToDetail = { movieId -> onNavigateToDetail(movieId) }
                    )
                }
                entry<NowPlaying> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Now Playing Movies",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                }
                entry<Upcoming> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Upcoming Movies",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                }
            }
        )
    }
}