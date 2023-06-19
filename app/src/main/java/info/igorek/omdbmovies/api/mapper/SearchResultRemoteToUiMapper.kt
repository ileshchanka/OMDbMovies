package info.igorek.omdbmovies.api.mapper

import info.igorek.omdbmovies.api.model.remote.SearchResultRemote
import info.igorek.omdbmovies.api.model.ui.SearchResultUi
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi
import javax.inject.Inject

class SearchResultRemoteToUiMapper @Inject constructor() {

    fun map(remote: SearchResultRemote): SearchResultUi {
        return with(remote) {
            SearchResultUi(
                movieResults = movieResults?.map { remote ->
                    MovieResultUi(
                        title = remote.title.orEmpty(),
                        year = remote.year.orEmpty(),
                        imdbID = remote.imdbID.orEmpty(),
                        type = remote.type.orEmpty(),
                        poster = remote.poster.orEmpty(),
                    )
                }.orEmpty(),
                totalResults = totalResults.orEmpty(),
                response = response.orEmpty(),
            )
        }
    }
}
