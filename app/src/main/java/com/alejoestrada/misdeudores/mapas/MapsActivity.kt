package com.alejoestrada.misdeudores.mapas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Restaurante

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true

        loadData()

        // Add a marker in Sydney and move the camera
       /* val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    private fun loadData() {
        val database = FirebaseDatabase.getInstance()
        val myRestauranteRef = database.getReference("restaurantes")
        var snapshot = ""

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.child("veganos").children) {
                    val restauranteServer: Restaurante? = dato.getValue(Restaurante::class.java)
                    restauranteServer?.let {
                        val pos = LatLng(restauranteServer.latitud, restauranteServer.longitud)
                        mMap.addMarker(MarkerOptions().position(pos).title(restauranteServer.id))
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos))
                    }

                }


            }

            override fun onCancelled(error: DatabaseError) {
            }


        }
        myRestauranteRef.addValueEventListener(postListener)
    }
}