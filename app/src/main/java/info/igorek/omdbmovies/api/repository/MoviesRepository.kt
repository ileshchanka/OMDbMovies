package info.igorek.omdbmovies.api.repository

import info.igorek.omdbmovies.api.OmdbApi
import info.igorek.omdbmovies.api.mapper.MovieDetailsRemoteToUiMapper
import info.igorek.omdbmovies.api.mapper.SearchResultRemoteToUiMapper
import info.igorek.omdbmovies.api.model.ui.MovieDetailsUi
import info.igorek.omdbmovies.api.model.ui.SearchResultUi
import javax.inject.Inject

interface MoviesRepository {
    suspend fun search(s: String): SearchResultUi
    suspend fun getById(t: String): MovieDetailsUi
}

class MoviesRepositoryImpl @Inject constructor(
    private val api: OmdbApi,
    private val searchResultsRemoteToUiMapper: SearchResultRemoteToUiMapper,
    private val moviesDetailsRemoteToUiMapper: MovieDetailsRemoteToUiMapper,
//    private val dao: RatesDao,
) : MoviesRepository {

    override suspend fun search(s: String): SearchResultUi {

        val table = searchResultsRemoteToUiMapper.map(
            api.search(s)
        )
//
//        dao.insertAll(table.rates)

        return table
    }

    override suspend fun getById(t: String): MovieDetailsUi {
        val movie = moviesDetailsRemoteToUiMapper.map(
            api.getById(t)
        )

        return movie
    }
}
