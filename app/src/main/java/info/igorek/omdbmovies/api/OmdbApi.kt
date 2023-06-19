package info.igorek.omdbmovies.api

import info.igorek.omdbmovies.api.model.remote.MovieDetailsRemote
import info.igorek.omdbmovies.api.model.remote.SearchResultRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    suspend fun search(
        @Query("s") searchString: String,
    ): SearchResultRemote

    @GET("/")
    suspend fun getById(
        @Query("i") id: String,
        @Query("plot") plot: String = "full",
    ): MovieDetailsRemote
}