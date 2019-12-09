package userdemoscreens

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anagha.dabbamen.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.*


class Dashboard : AppCompatActivity() {

    private var content: FrameLayout? = null
    var bottomNavigationView: BottomNavigationView? = null;
    // inside a basic activity
    private var locationManager: LocationManager? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
        // Create persistent LocationManager reference
        getLastKnownLocation()
        addBadgeView();

    }

    /**
     * call this method for receive location
     * get location and give callback when successfully retrieve
     * function itself check location permission before access related methods
     *
     */
    fun getLastKnownLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION
            )
            return
        }
        //locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)


        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    // use your location object
                    // get latitude , longitude and other info from this
                    Toast.makeText(
                        applicationContext,
                        "lat:" + location.latitude + "long:" + location.longitude,
                        Toast.LENGTH_LONG
                    ).show()
                    bottomnavigationFragmentsSwitching();
                } else {
                    bottomnavigationFragmentsSwitching();
                }

            }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            //.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLastKnownLocation()
                PackageManager.PERMISSION_DENIED -> getLastKnownLocation() //Tell to user the need of grant permission
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    fun bottomnavigationFragmentsSwitching() {
        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                com.anagha.dabbamen.R.id.navigation_explore -> {
                    val fragment = ExploreFragment()
                    addFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                com.anagha.dabbamen.R.id.navigation_cart -> {
                    val fragment = CartFragment()
                    addFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_schedule -> {
                    val fragment = ScheduleFragment()
                    addFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_feedback -> {
                    val fragment = FeedBackFragment()
                    addFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    val fragment = ProfileFragment()
                    addFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }
        val fragment = ExploreFragment()
        addFragment(fragment)
    }


    private fun addBadgeView() {
        // bottomNavigationView?.getOrCreateBadge(R.id.navigation_cart)?.number

    }

    /*private fun refreshBadgeView() {
        val badgeIsVisible = notificationBadge?.getVisibility() ?:  !== VISIBLE
        if (notificationBadge == null) {
        } else {
            notificationBadge.setVisibility(if (badgeIsVisible) VISIBLE else GONE)
        }
        //button.setText(if (badgeIsVisible) "Hide badge" else " Show badge")
    }*/
}


