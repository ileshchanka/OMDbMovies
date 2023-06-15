package info.igorek.omdbmovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.igorek.omdbmovies.api.repository.MoviesRepository
import javax.inject.Inject

class HomeScreenViewModelFactory @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(
            moviesRepository = moviesRepository,
        ) as T
    }
}
