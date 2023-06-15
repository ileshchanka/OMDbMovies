package info.igorek.omdbmovies

import android.app.Application
import info.igorek.omdbmovies.di.AppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.init(applicationContext)
    }
}
