package info.igorek.omdbmovies.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import info.igorek.omdbmovies.EMPTY_STRING
import info.igorek.omdbmovies.NAV_DETAILS

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    navController: NavController,
) {
    val state by viewModel.state.collectAsState()
    val text = remember { mutableStateOf(EMPTY_STRING) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
    ) {
        item {
            OutlinedTextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
            )
            Button(
                onClick = {
                    viewModel.onFindButtonPressed(text.value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                Text(
                    text = "Find",
                    modifier = Modifier
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                )
            }
        }

        items(state.movieList) { movie ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {

                    navController.navigate("$NAV_DETAILS/${movie.imdbID}")

                }
            ) {
                GlideImage(
                    model = movie.poster,
                    contentDescription = "${movie.title} Poster",
                )
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }
}
