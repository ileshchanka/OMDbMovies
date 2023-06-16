package info.igorek.omdbmovies.api.repository

import info.igorek.omdbmovies.api.OmdbApi
import info.igorek.omdbmovies.api.mapper.SearchResultRemoteToUiMapper
import info.igorek.omdbmovies.api.model.ui.SearchResultUi
import javax.inject.Inject

interface MoviesRepository {
    suspend fun search(s: String): SearchResultUi
}

class MoviesRepositoryImpl @Inject constructor(
    private val api: OmdbApi,
    private val moviesRemoteToUiMapper: SearchResultRemoteToUiMapper,
//    private val dao: RatesDao,
) : MoviesRepository {

    override suspend fun search(s: String): SearchResultUi {

        val table = moviesRemoteToUiMapper.map(
            api.search(s)
        )
//
//        dao.insertAll(table.rates)

        return table
    }
}
