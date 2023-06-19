package info.igorek.omdbmovies.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
) {
    val state by viewModel.state.collectAsState()

    GlideImage(
        model = state.poster,
        contentDescription = "${state.title} Poster"
    )
    Text(text = state.title)
    Text(text = state.plot)
}
