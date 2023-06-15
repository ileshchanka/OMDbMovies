package info.igorek.omdbmovies.api

import info.igorek.omdbmovies.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class KeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
