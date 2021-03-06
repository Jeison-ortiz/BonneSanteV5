package com.alejoestrada.misdeudores.mapas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Restaurante
import com.alejoestrada.misdeudores.databinding.FragmentMapaRestauranteBinding
import com.alejoestrada.misdeudores.mapas.MapaRestauranteFragmentArgs
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


class MapaRestauranteFragment : Fragment() {
    private lateinit var binding : FragmentMapaRestauranteBinding
    private lateinit var restauranteSeleccionado : String
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
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val database = FirebaseDatabase.getInstance()
        val myRestauranteRef = database.getReference("restaurantes")
        var snapshot = ""

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.child(restauranteSeleccionado).children) {
                    val restauranteServer: Restaurante? = dato.getValue(Restaurante::class.java)
                    restauranteServer?.let {
                        val pos = LatLng(restauranteServer.latitud,restauranteServer.longitud)
                        if(restauranteSeleccionado == "vegetarianos"){
                            googleMap.addMarker(
                                MarkerOptions().icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_GREEN)).position(pos).title(restauranteServer.id))
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,12.79f))
                        }else if (restauranteSeleccionado == "veganos"){
                            googleMap.addMarker(
                                MarkerOptions().icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_RED)).position(pos).title(restauranteServer.id))
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,12.79f))
                        }else{
                            googleMap.addMarker(
                                MarkerOptions().icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_VIOLET)).position(pos).title(restauranteServer.id))
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,12.79f))
                        }

                    }

                }


            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
        myRestauranteRef.addValueEventListener(postListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa_restaurante, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentMapaRestauranteBinding.bind(view)
        val args : MapaRestauranteFragmentArgs by navArgs()
        restauranteSeleccionado = args.tipoRestaurante




        if(restauranteSeleccionado == "vegetarianos"){
            (activity as AppCompatActivity).supportActionBar?.title = "Vegetarianos"
        }else if (restauranteSeleccionado == "veganos"){
            (activity as AppCompatActivity).supportActionBar?.title = "Veganos"
        }else{
            (activity as AppCompatActivity).supportActionBar?.title = "Flexibles"
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


}