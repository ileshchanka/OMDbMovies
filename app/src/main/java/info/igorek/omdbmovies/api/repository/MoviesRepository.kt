package info.igorek.omdbmovies.api.repository

import info.igorek.omdbmovies.api.OmdbApi
import info.igorek.omdbmovies.api.model.remote.SearchResultRemote
import javax.inject.Inject

interface MoviesRepository {
    suspend fun search(s: String): SearchResultRemote
}

class MoviesRepositoryImpl @Inject constructor(
    private val api: OmdbApi,
//    private val tableRemoteToUiMapper: TableRemoteToUiMapper,
//    private val dao: RatesDao,
) : MoviesRepository {

    override suspend fun search(s: String): SearchResultRemote {

//        val table = tableRemoteToUiMapper.map(
            return api.search(s)
//        )
//
//        dao.insertAll(table.rates)

//        return table
    }
}
