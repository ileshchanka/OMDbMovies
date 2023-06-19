package info.igorek.omdbmovies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import info.igorek.omdbmovies.api.repository.MoviesRepository

class DetailsScreenViewModelFactory @AssistedInject constructor(
    private val moviesRepository: MoviesRepository,
    @Assisted("DetailsScreenViewModelFactory.id") private val id: String,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsScreenViewModel(
            moviesRepository = moviesRepository,
            id = id,
        ) as T
    }

    @AssistedFactory
    interface AssistFactory {
        fun create(
            @Assisted("DetailsScreenViewModelFactory.id") id: String,
        ): DetailsScreenViewModelFactory
    }
}
