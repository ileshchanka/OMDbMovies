package info.igorek.omdbmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import info.igorek.omdbmovies.di.AppComponent
import info.igorek.omdbmovies.ui.details.DetailsScreen
import info.igorek.omdbmovies.ui.home.HomeScreen
import info.igorek.omdbmovies.ui.theme.OMDbMoviesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModelFactory = AppComponent.get().getHomeScreenViewModelFactory()
        val detailsScreenViewModelFactory = AppComponent.get().getDetailsScreenViewModelFactory()

        setContent {
            OMDbMoviesTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NAV_HOME,
                    ) {
                        composable(
                            route = NAV_HOME
                        ) {
                            HomeScreen(
                                viewModel = viewModel(factory = homeViewModelFactory),
                                navController = navController,
                            )
                        }
                        composable(
                            route = "$NAV_DETAILS/{$NAV_DETAILS_ID}",
                            arguments = listOf(navArgument(NAV_DETAILS_ID) { type = NavType.StringType })
                        ) {
                            DetailsScreen(
                                viewModel = viewModel(
                                    factory = detailsScreenViewModelFactory.create(
                                        id = it.arguments?.getString(NAV_DETAILS_ID).orEmpty(),
                                    )
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}
