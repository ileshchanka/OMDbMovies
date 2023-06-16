package info.igorek.omdbmovies.api.mapper

import info.igorek.omdbmovies.api.model.remote.SearchResultRemote
import info.igorek.omdbmovies.api.model.ui.SearchResultUi
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi
import javax.inject.Inject

class SearchResultRemoteToUiMapper @Inject constructor() {

    fun map(remote: SearchResultRemote): SearchResultUi {
        return with(remote) {
            SearchResultUi(
                movieResults = movieResults.map { remote ->
                    MovieResultUi(
                        title = remote.title,
                        year = remote.year,
                        imdbID = remote.imdbID,
                        type = remote.type,
                        poster = remote.poster,
                    )
                },
                totalResults = totalResults,
                response = response,
            )
        }
    }
}
