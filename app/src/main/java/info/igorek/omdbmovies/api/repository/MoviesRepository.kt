package info.igorek.omdbmovies.api.repository

import info.igorek.omdbmovies.db.MoviesDao
import info.igorek.omdbmovies.api.OmdbApi
import info.igorek.omdbmovies.api.mapper.MovieDetailsRemoteToUiMapper
import info.igorek.omdbmovies.api.mapper.SearchResultRemoteToUiMapper
import info.igorek.omdbmovies.api.model.ui.MovieDetailsUi
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi
import javax.inject.Inject

interface MoviesRepository {
    suspend fun search(s: String): List<MovieResultUi>
    suspend fun getById(t: String): MovieDetailsUi
    suspend fun getFromDB(): List<MovieResultUi>
}

class MoviesRepositoryImpl @Inject constructor(
    private val api: OmdbApi,
    private val searchResultsRemoteToUiMapper: SearchResultRemoteToUiMapper,
    private val moviesDetailsRemoteToUiMapper: MovieDetailsRemoteToUiMapper,
    private val dao: MoviesDao,
) : MoviesRepository {

    override suspend fun search(s: String): List<MovieResultUi> {

        try {
            val movieList = searchResultsRemoteToUiMapper.map(
                api.search(s)
            ).movieResults

            dao.deleteAll()
            dao.insertAll(movieList)
        } catch (_: Exception) {

        }
        return dao.getAll()
    }

    override suspend fun getById(t: String): MovieDetailsUi {
        val movie = moviesDetailsRemoteToUiMapper.map(
            api.getById(t)
        )

        return movie
    }

    override suspend fun getFromDB(): List<MovieResultUi> {
        return dao.getAll()
    }
}
