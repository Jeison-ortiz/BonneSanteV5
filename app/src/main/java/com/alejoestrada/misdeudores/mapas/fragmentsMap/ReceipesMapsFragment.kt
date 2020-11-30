package com.alejoestrada.misdeudores.mapas.fragmentsMap

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Restaurante

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReceipesMapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
       /* val sydney = LatLng(6.2460029, -75.5906848)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        val database = FirebaseDatabase.getInstance()
        val myRestauranteRef = database.getReference("restaurantes")
        var snapshot = ""

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.child("flexibles").children) {
                    val restauranteServer: Restaurante? = dato.getValue(Restaurante::class.java)
                    restauranteServer?.let {
                        val pos = LatLng(restauranteServer.latitud,restauranteServer.longitud)
                        googleMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).position(pos).title(restauranteServer.id))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,12.79f))
                    }

                }


            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
        myRestauranteRef.addValueEventListener(postListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_receipes_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Flexibles"

        val mapFragment = childFragmentManager.findFragmentById(R.id.ReceipesMapsFragment) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}