package info.igorek.omdbmovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.igorek.omdbmovies.api.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    companion object {
        const val TABLE_TYPE = "A"
    }

//    data class State(
//        val table: String = EMPTY_STRING,
//        val no: String = EMPTY_STRING,
//        val effectiveDate: String = EMPTY_STRING,
//        val rateList: List<RateUi> = emptyList(),
//    )

//    private val _state = MutableStateFlow(State())
//    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val item = moviesRepository.search("tron")
            item

//            _state.update {
//                it.copy(
//                    table = item.table,
//                    no = item.no,
//                    effectiveDate = item.effectiveDate,
//                    rateList = item.rates,
//                )
//            }
        }
    }
}
