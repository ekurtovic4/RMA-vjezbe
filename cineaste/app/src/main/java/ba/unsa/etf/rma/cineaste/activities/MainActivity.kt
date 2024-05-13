package ba.unsa.etf.rma.cineaste.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.adapters_etc.MyBroadcastReceiver
import ba.unsa.etf.rma.cineaste.services.LatestMovieService
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val myBroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navView.setupWithNavController(navController)

        if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
        {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                val bundle = bundleOf("search" to it)
                navView.selectedItemId= R.id.searchFragment
                navController.navigate(R.id.searchFragment,bundle)
            }
        }

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myBroadcastReceiver, filter)

        Intent(this, LatestMovieService::class.java).also {
            startForegroundService(it)
            return
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }
}