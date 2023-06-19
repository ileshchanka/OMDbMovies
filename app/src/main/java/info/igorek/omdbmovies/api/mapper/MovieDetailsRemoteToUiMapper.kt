package info.igorek.omdbmovies.api.mapper

import info.igorek.omdbmovies.api.model.remote.MovieDetailsRemote
import info.igorek.omdbmovies.api.model.ui.MovieDetailsUi
import info.igorek.omdbmovies.api.model.ui.MovieDetailsUi.RatingUi
import javax.inject.Inject

class MovieDetailsRemoteToUiMapper @Inject constructor() {

    fun map(remote: MovieDetailsRemote): MovieDetailsUi {
        return with(remote) {
            MovieDetailsUi(
                title = title.orEmpty(),
                year = year.orEmpty(),
                rated = rated.orEmpty(),
                released = released.orEmpty(),
                runtime = runtime.orEmpty(),
                genre = genre.orEmpty(),
                director = director.orEmpty(),
                writer = writer.orEmpty(),
                actors = actors.orEmpty(),
                plot = plot.orEmpty(),
                language = language.orEmpty(),
                country = country.orEmpty(),
                awards = awards.orEmpty(),
                poster = poster.orEmpty(),
                ratings = ratings?.map { remote ->
                    RatingUi(
                        source = remote.source.orEmpty(),
                        value = remote.value.orEmpty(),
                    )
                }.orEmpty(),
                metascore = metascore.orEmpty(),
                imdbRating = imdbRating.orEmpty(),
                imdbVotes = imdbVotes.orEmpty(),
                imdbID = imdbID.orEmpty(),
                type = type.orEmpty(),
                dVD = dVD.orEmpty(),
                boxOffice = boxOffice.orEmpty(),
                production = production.orEmpty(),
                website = website.orEmpty(),
                response = response.orEmpty(),
            )
        }
    }
}