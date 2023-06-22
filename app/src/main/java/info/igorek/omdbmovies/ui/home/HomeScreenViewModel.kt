package info.igorek.omdbmovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.igorek.omdbmovies.NetworkStateProvider
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi
import info.igorek.omdbmovies.api.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val networkStateProvider: NetworkStateProvider,
) : ViewModel() {

    data class State(
        val movieList: List<MovieResultUi> = emptyList(),
        val isConnectionAvailable: Boolean = false,
    )

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            networkStateProvider.stateFlow.collect { isConnectionAvailable ->
                if (isConnectionAvailable.not()) {
                    viewModelScope.launch(Dispatchers.IO) {
                        _state.update {
                            it.copy(movieList = moviesRepository.getFromDB())
                        }
                    }
                }

                _state.update {
                    it.copy(isConnectionAvailable = isConnectionAvailable)
                }
            }
        }
    }

    fun onFindButtonPressed(s: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(movieList = moviesRepository.search(s))
            }
        }
    }
}
