package info.igorek.omdbmovies.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
    ],
)
@Singleton
interface AppComponent {

    companion object {
        private var component: AppComponent? = null

        fun init(context: Context) {
            component = DaggerAppComponent.builder()
                .context(context)
                .build().apply {
                    component = this
                }
        }

        fun get(): AppComponent {
            return component ?: throw NotImplementedError("You should call 'init' method")
        }
    }

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

//    fun getHomeScreenViewModelFactory(): HomeScreenViewModelFactory
}
