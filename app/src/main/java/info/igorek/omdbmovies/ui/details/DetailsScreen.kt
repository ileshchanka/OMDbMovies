package info.igorek.omdbmovies.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
    id: String?,
) {
    val state by viewModel.state.collectAsState()
//    val text = remember { mutableStateOf(EMPTY_STRING) }

    Text(text = id.toString())
}
