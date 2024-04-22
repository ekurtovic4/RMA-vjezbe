package ba.unsa.etf.rma.cineaste.adapters_etc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isConnected = isConnectedOrConnecting(context)
        if (!isConnected) {
            Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show()
        }
    }

    private fun isConnectedOrConnecting(context: Context?): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connMgr.activeNetwork
        val networkCapabilities = connMgr.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}
