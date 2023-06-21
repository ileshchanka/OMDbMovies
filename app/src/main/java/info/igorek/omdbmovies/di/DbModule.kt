package info.igorek.omdbmovies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import info.igorek.omdbmovies.db.MoviesDb
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        MoviesDb::class.java,
        "movies.db",
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDao(db: MoviesDb) = db.moviesDao()
}
