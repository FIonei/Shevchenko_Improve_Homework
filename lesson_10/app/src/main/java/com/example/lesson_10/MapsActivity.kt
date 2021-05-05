package com.example.lesson_10

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var mService: GetItemsService
    private lateinit var items: List<BridgeInfoItem>
    private lateinit var thread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        thread = Thread {
            mService = Common.retrofitService
            downloadText()
        }
        thread.start()
        thread.join()
        val updateHandler = Handler()
        val runnable = Runnable {
            mapFragment.getMapAsync(this)
        }
        updateHandler.postDelayed(runnable, 4000)
    }

    private fun downloadText() {
        mService.getAllItems().enqueue(object : Callback<MutableList<BridgeInfoItem>> {
            override fun onFailure(call: Call<MutableList<BridgeInfoItem>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<MutableList<BridgeInfoItem>>,
                response: Response<MutableList<BridgeInfoItem>>
            ) {
                items = response.body() as MutableList<BridgeInfoItem>
            }
        }
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val bridges: MutableList<LatLng> = mutableListOf()
        for (i in items) bridges.add(LatLng(i.lat!!, i.lng!!))
        for (i in bridges) mMap.addMarker(
            MarkerOptions().position(i).title(
                items[bridges.indexOf(
                    i
                )].bridgeName
            )
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bridges[0]))
        mMap.setMinZoomPreference(5.0F)
        mMap.setMaxZoomPreference(20.0F)
    }
}