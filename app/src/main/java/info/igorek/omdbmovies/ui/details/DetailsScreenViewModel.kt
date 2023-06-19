package info.igorek.omdbmovies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi
import info.igorek.omdbmovies.api.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    data class State(
        val movieList: List<MovieResultUi> = emptyList(),
    )

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    fun onFindButtonPressed(s: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = moviesRepository.search(s)

            _state.update {
                it.copy(
                    movieList = item.movieResults,
                )
            }
        }
    }
}
