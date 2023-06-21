package info.igorek.omdbmovies.api.model.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SearchResultUi(
    val movieResults: List<MovieResultUi>,
    val totalResults: String,
    val response: String,
) {
    @Entity(tableName = "movies")
    data class MovieResultUi(
        val title: String,
        val year: String,
        @PrimaryKey val imdbID: String,
        val type: String,
        val poster: String,
    )
}