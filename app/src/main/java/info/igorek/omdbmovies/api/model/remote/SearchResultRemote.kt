package info.igorek.omdbmovies.api.model.remote

import com.google.gson.annotations.SerializedName

data class SearchResultRemote(
    @SerializedName("Search")
    val movieResults: List<MovieResult>,
    @SerializedName("totalResults")
    val totalResults: String,
    @SerializedName("Response")
    val response: String,
) {
    data class MovieResult(
        @SerializedName("Title")
        val title: String,
        @SerializedName("Year")
        val year: String,
        @SerializedName("imdbID")
        val imdbID: String,
        @SerializedName("Type")
        val type: String,
        @SerializedName("Poster")
        val poster: String,
    )
}