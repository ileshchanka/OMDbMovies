package info.igorek.omdbmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi

@Database(
    entities = [MovieResultUi::class],
    version = 1,
)
abstract class MoviesDb : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}
