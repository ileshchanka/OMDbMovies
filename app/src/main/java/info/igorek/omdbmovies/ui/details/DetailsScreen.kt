package info.igorek.omdbmovies.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import info.igorek.omdbmovies.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
) {
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
    ) {
        item {
            GlideImage(
                model = state.poster,
                contentDescription = stringResource(R.string.title_poster, state.title),
                modifier = Modifier.height(300.dp),
            )
            Text(
                text = state.title, fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(state.ratingList) { rating ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = rating.source)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = rating.value)
            }
        }

        item {
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = state.plot)
        }
    }
}
