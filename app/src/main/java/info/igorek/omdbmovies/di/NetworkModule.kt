package info.igorek.omdbmovies.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import info.igorek.omdbmovies.BASE_URL
import info.igorek.omdbmovies.api.KeyInterceptor
import info.igorek.omdbmovies.api.OmdbApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {
        private const val timeoutSeconds = 60L

        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        @Singleton
        fun provideGsonConverterFactory(): Converter.Factory {
            return GsonConverterFactory.create()
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            keyInterceptor: Interceptor,
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .addInterceptor(keyInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideBaseRestApiService(
            okHttpClient: OkHttpClient,
            gsonFactory: Converter.Factory,
        ): OmdbApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonFactory)
                .client(okHttpClient)
                .build()
            return retrofit.create(OmdbApi::class.java)
        }
    }

    @Binds
    @Singleton
    fun bindsKeyInterceptor(interceptor: KeyInterceptor): Interceptor
}
