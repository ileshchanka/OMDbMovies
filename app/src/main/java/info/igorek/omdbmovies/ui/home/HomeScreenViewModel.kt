package info.igorek.omdbmovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi
import info.igorek.omdbmovies.api.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    data class State(
        val movieList: List<MovieResultUi> = emptyList(),
    )

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    movieList = moviesRepository.getFromDB()
                )
            }
        }
    }

    fun onFindButtonPressed(s: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieList = moviesRepository.search(s)

            _state.update {
                it.copy(
                    movieList = movieList,
                )
            }
        }
    }
}
