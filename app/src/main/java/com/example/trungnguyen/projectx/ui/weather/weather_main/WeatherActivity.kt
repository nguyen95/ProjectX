package com.example.trungnguyen.projectx.ui.weather.weather_main

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.example.trungnguyen.projectx.R
import kotlinx.android.synthetic.main.weather_item_main.*
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import timber.log.Timber.e
import java.util.*


/**
 * Created by Trung Nguyen on 20-Jun-18.
 */
class WeatherActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private val UPDATE_INTERVAL: Long = 5000
    private val FASTEST_INTERVAL: Long = 5000
    private val REQUEST_LOCATION_PERMISSION = 100

    private var mGoogleApiClient: GoogleApiClient? = null
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLastLocation: Location
    private var mIsAutoUpdateLocation = true

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_layout_main)
        ButterKnife.bind(this)

        hk_time.timeZone = TimeZone.getDefault().toString()

        e(TimeZone.getDefault().toString())

        requestLocationPermissions();

        if (isPlayServicesAvailable()) {
            setUpLocationClientIfNeeded()
            buildLocationRequest()
        } else {
            Toast.makeText(this, "Device does not support Google Play services", Toast.LENGTH_LONG).show()
        }

        updateUiRuntime()

        layout_main.setOnClickListener { view ->

        }
    }

    private fun requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION ->
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
        }
    }

    private fun updateUi() {
        if (isGpsOn()) {

        }
    }

    private fun updateUiRuntime() {
//        runOnUiThread {
//            while (true) {
//                e("runnnnnnUi")
//                Thread.sleep(1000)
//            }
//        }
    }

    private fun isPlayServicesAvailable(): Boolean {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
    }

    private fun isGpsOn(): Boolean {
        var manager = getSystemService(LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun setUpLocationClientIfNeeded() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build()
        }
    }

    private fun buildLocationRequest() {
        mLocationRequest = LocationRequest.create()
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        mLocationRequest.setInterval(UPDATE_INTERVAL)
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL)
    }

    protected fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
    }

    protected fun stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient?.connect()
    }

    override fun onStop() {
        mGoogleApiClient?.disconnect()
        super.onStop()
    }

    override fun onConnected(p0: Bundle?) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        startLocationUpdates()

        var lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        if (lastLocation != null) {
            mLastLocation = lastLocation
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient?.connect()
    }

    override fun onLocationChanged(location: Location) {
        mLastLocation = location
        if (mIsAutoUpdateLocation) {
            updateUi()
        }
    }


    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onResume() {
        super.onResume()
        if (mGoogleApiClient != null && !mGoogleApiClient!!.isConnected()) {
            mGoogleApiClient!!.connect();
        }
    }

    override fun onDestroy() {
        if (mGoogleApiClient != null && mGoogleApiClient!!.isConnected()) {
            stopLocationUpdates()
            mGoogleApiClient!!.disconnect()
            mGoogleApiClient = null
        }
        super.onDestroy()
    }

}