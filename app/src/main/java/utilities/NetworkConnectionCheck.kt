package utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object NetworkConnectionCheck {
    fun checkInternetConnection(_ctx: Context): Boolean {
        val cm = _ctx
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}
