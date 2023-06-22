package info.igorek.omdbmovies.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.igorek.omdbmovies.EMPTY_STRING
import info.igorek.omdbmovies.api.model.ui.MovieDetailsUi.RatingUi
import info.igorek.omdbmovies.api.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val moviesRepository: MoviesRepository,
    private val id: String,
) : ViewModel() {

    data class State(
        val poster: String = EMPTY_STRING,
        val title: String = EMPTY_STRING,
        val ratingList: List<RatingUi> = emptyList(),
        val plot: String = EMPTY_STRING,
        val isDataAvailable: Boolean = false,
    )

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val item = moviesRepository.getById(id)

            item?.let {
                _state.update {
                    it.copy(
                        poster = item.poster,
                        title = item.title,
                        ratingList = item.ratings,
                        plot = item.plot,
                    )
                }
            }

            _state.update {
                it.copy(
                    isDataAvailable = item != null,
                )
            }
        }
    }
}
