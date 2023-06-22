package info.igorek.omdbmovies

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NetworkStateProvider @Inject constructor(
    private val context: Context,
) {

    private val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private val _state = MutableStateFlow(context.isNetworkAvailable())
    val stateFlow: StateFlow<Boolean> = _state.asStateFlow()

    init {
        _state.subscriptionCount.map { count ->
                count > 0
            }.distinctUntilChanged().onEach { isActive ->
                if (isActive) subscribe() else unsubscribe()
            }.launchIn(CoroutineScope(SupervisorJob() + Dispatchers.Main))
    }

    private fun subscribe() {
        if (networkCallback != null) return

        networkCallback = NetworkCallbackImpl().also {
            cm.registerDefaultNetworkCallback(it)
        }

        _state.tryEmit(context.isNetworkAvailable())
    }

    private fun unsubscribe() {
        if (networkCallback == null) return

        networkCallback?.run {
            cm.unregisterNetworkCallback(this)
        }
        networkCallback = null
    }

    private inner class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            _state.tryEmit(true)
        }

        override fun onLost(network: Network) {
            _state.tryEmit(false)
        }
    }
}

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = cm.activeNetwork
    val capabilities = cm.getNetworkCapabilities(network)
    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
        NetworkCapabilities.TRANSPORT_CELLULAR,
    ))
}
