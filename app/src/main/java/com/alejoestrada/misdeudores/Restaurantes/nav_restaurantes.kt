package com.alejoestrada.misdeudores.Restaurantes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Restaurante
import com.alejoestrada.misdeudores.databinding.FragmentRestauranteBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class nav_restaurantes : Fragment() {

    private lateinit var binding: FragmentRestauranteBinding
    var listRestaurante: MutableList<Restaurante> = mutableListOf()
    private lateinit var restaurantesRVAdapter: RestaurantesRVAdapter

    val listTipo = listOf("vegetarianos","flexibles", "veganos")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurante, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRestauranteBinding.bind(view)
        binding.restaurantesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.restaurantesRecyclerView.setHasFixedSize(true) // para que el tamaño sea constante
        restaurantesRVAdapter = RestaurantesRVAdapter(listRestaurante as ArrayList<Restaurante>)
        binding.restaurantesRecyclerView.adapter = restaurantesRVAdapter
        cargarDesdeFarebase()
        restaurantesRVAdapter.notifyDataSetChanged() // se hace para notificar que cambiaron los datos porque se envio una lista vacia
    }

    private fun cargarDesdeFarebase() {
        val database = FirebaseDatabase.getInstance()
        val myRestauranteRef = database.getReference("restaurantes")
        var snapshot = ""
        listRestaurante.clear()

        for (tipo in listTipo){
            val postListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dato: DataSnapshot in snapshot.child(tipo).children) {
                        val restauranteServer:Restaurante? = dato.getValue(Restaurante::class.java)
                        restauranteServer?.let { listRestaurante.add(it) }
                    }
                    restaurantesRVAdapter.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {
                }

            }
            myRestauranteRef.addValueEventListener(postListener)
         }
    }

}