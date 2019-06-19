package com.onething.lostlocationsample

import android.Manifest
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import com.mapzen.android.lost.api.LostApiClient
import com.mapzen.android.lost.api.LocationServices
import android.widget.Toast
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.util.Log
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import com.mapzen.android.lost.api.LocationServices.FusedLocationApi
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), LostApiClient.ConnectionCallbacks {

    private lateinit var lostApiClient : LostApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        lostApiClient = LostApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .build()
        lostApiClient.connect()

        fab.setOnClickListener { view ->

        }

    }

    override fun onConnected() {
        val location = FusedLocationApi.getLastLocation(lostApiClient)
        if (location != null) {
            tv_location.text = "${location.latitude} and ${location.longitude}"
        }
        Log.i("MainActivity", " ==> Connected")
    }

    override fun onConnectionSuspended() {
        Log.i("MainActivity", " ==> onConnectionSuspended")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
