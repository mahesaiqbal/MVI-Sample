package com.mahesaiqbal.mvisample.presentation.view.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahesaiqbal.mvisample.R
import com.mahesaiqbal.mvisample.presentation.ui.components.BottomNavigationBar
import com.mahesaiqbal.mvisample.presentation.ui.components.CenterTopAppBar
import com.mahesaiqbal.mvisample.presentation.ui.navigation.Screen
import com.mahesaiqbal.mvisample.presentation.view.moviedetail.MovieDetailScreen
import com.mahesaiqbal.mvisample.presentation.view.popularmovie.PopularMovieScreen
import com.mahesaiqbal.mvisample.utils.MovieConstants.DEFAULT_INT
import com.mahesaiqbal.mvisample.utils.MovieConstants.MOVIE_ID
import com.mahesaiqbal.mvisample.utils.MovieConstants.NAV_HOST_ROUTE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val context = LocalContext.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (currentRoute) {
                Screen.Popular.route -> {
                    CenterTopAppBar(title = context.resources.getString(R.string.app_name))
                }
            }
        },
        bottomBar = {
            when (currentRoute) {
                Screen.Popular.route -> {
                    BottomNavigationBar(
                        currentRoute = currentRoute,
                        onNavigationSelected = { itemRoute ->
                            navController.navigate(itemRoute) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            route = NAV_HOST_ROUTE,
            navController = navController,
            startDestination = Screen.Popular.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Popular.route) {
                PopularMovieScreen(
                    onShowInfo = { message ->
                        scope.launch { snackbarHostState.showSnackbar(message) }
                    },
                    onNavigateToDetail = { movieId ->
                        navController.navigate(Screen.MovieDetails.createRoute(movieId))
                    }
                )
            }
            composable(
                route = Screen.MovieDetails.route,
                arguments = listOf(navArgument(MOVIE_ID) { type = NavType.IntType })
            ) {
                val movieId = it.arguments?.getInt(MOVIE_ID) ?: DEFAULT_INT
                MovieDetailScreen(
                    movieId = movieId,
                    onNavigateBack = { navController.navigateUp() },
                    onShowInfo = { message ->
                        scope.launch { snackbarHostState.showSnackbar(message) }
                    }
                )
            }
        }
    }
}