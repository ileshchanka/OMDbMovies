package info.igorek.omdbmovies.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.igorek.omdbmovies.api.model.ui.SearchResultUi.MovieResultUi

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rateList: List<MovieResultUi>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieResultUi>
}
