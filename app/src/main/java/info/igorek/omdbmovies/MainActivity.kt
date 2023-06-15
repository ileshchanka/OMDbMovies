package info.igorek.omdbmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import info.igorek.omdbmovies.di.AppComponent
import info.igorek.omdbmovies.ui.home.HomeScreen
import info.igorek.omdbmovies.ui.theme.OMDbMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = AppComponent.get().getHomeScreenViewModelFactory()

        setContent {
            OMDbMoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    HomeScreen(
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }
            }
        }
    }
}