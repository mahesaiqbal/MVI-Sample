package com.mahesaiqbal.mvisample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mahesaiqbal.mvisample.presentation.ui.components.BottomNavigationBar
import com.mahesaiqbal.mvisample.presentation.ui.components.CenterTopAppBar
import com.mahesaiqbal.mvisample.presentation.ui.navigation.Screen
import com.mahesaiqbal.mvisample.presentation.ui.theme.MVISampleTheme
import com.mahesaiqbal.mvisample.presentation.view.main.MovieApp
import com.mahesaiqbal.mvisample.presentation.view.popularmovie.PopularMovieScreen
import com.mahesaiqbal.mvisample.utils.MovieConstants.NAV_HOST_ROUTE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVISampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MovieApp()
                }
            }
        }
    }
}