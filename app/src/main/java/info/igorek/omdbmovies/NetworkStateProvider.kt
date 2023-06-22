package info.igorek.omdbmovies

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface NetworkStateFlow {

    val stateFlow: Flow<NetworkStatusState>
}

sealed class NetworkStatusState {
    object Connected : NetworkStatusState()
    object Disconnected : NetworkStatusState()
}

class NetworkStateProvider @Inject constructor(
    private val context: Context,
) : NetworkStateFlow {

    private val cm: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private val state = MutableStateFlow(currentNetwork)

    override val stateFlow: StateFlow<NetworkStatusState> = state.asStateFlow()

    init {
        state.subscriptionCount
            .map { count ->
                count > 0
            }
            .distinctUntilChanged()
            .onEach { isActive ->
                if (isActive) subscribe() else unsubscribe()
            }
            .launchIn(CoroutineScope(SupervisorJob() + Dispatchers.Main))
    }

    private val currentNetwork: NetworkStatusState
        get() = if (context.isNetworkAvailable()) {
            NetworkStatusState.Connected
        } else {
            NetworkStatusState.Disconnected
        }

    private fun subscribe() {
        if (networkCallback != null) return

        networkCallback = NetworkCallbackImpl().also {
            cm.registerDefaultNetworkCallback(it)
        }

        emitNetworkState(currentNetwork)
    }

    private fun unsubscribe() {
        if (networkCallback == null) return

        networkCallback?.run {
            cm.unregisterNetworkCallback(this)
        }
        networkCallback = null
    }

    private fun emitNetworkState(newState: NetworkStatusState) {
        state.tryEmit(newState)
    }

    private inner class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) = emitNetworkState(NetworkStatusState.Connected)

        override fun onLost(network: Network) = emitNetworkState(NetworkStatusState.Disconnected)
    }

}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR,
                )
                )
    } else {
        connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}
