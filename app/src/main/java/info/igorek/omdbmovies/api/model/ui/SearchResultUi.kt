package info.igorek.omdbmovies.api.model.ui

data class SearchResultUi(
    val movieResults: List<MovieResultUi>,
    val totalResults: String,
    val response: String,
) {
    data class MovieResultUi(
        val title: String,
        val year: String,
        val imdbID: String,
        val type: String,
        val poster: String,
    )
}