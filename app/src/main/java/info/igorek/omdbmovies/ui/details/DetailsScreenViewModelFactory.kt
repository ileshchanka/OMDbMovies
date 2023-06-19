package info.igorek.omdbmovies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.igorek.omdbmovies.api.repository.MoviesRepository
import javax.inject.Inject

class DetailsScreenViewModelFactory @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsScreenViewModel(
            moviesRepository = moviesRepository,
        ) as T
    }
}
