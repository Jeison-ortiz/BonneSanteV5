package com.alejoestrada.misdeudores.Recetas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Receta
import com.alejoestrada.misdeudores.data.server.Restaurante
import com.alejoestrada.misdeudores.databinding.FragmentListaRecetasBinding
import com.alejoestrada.misdeudores.databinding.FragmentMapaRestauranteBinding
import com.alejoestrada.misdeudores.mapas.MapaRestauranteFragmentArgs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ListaRecetasFragment: Fragment(), RecetasRVAdapter.OnItemClickListener {
    private lateinit var binding:FragmentListaRecetasBinding
    var listRecetas: MutableList<Receta> = mutableListOf()
    private  lateinit var recetasRVAdapter: RecetasRVAdapter
    private lateinit var tipoDeReceta: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_recetas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentListaRecetasBinding.bind(view)

        binding.RecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.RecyclerView.setHasFixedSize(true)

        recetasRVAdapter = RecetasRVAdapter(listRecetas as ArrayList<Receta>, this@ListaRecetasFragment)
        binding.RecyclerView.adapter = recetasRVAdapter
        cargarDesdeFirebase()
        recetasRVAdapter.notifyDataSetChanged()
        FragmentListaRecetasBinding.bind(view)

        val args: ListaRecetasFragmentArgs by navArgs()
        tipoDeReceta = args.tipoRecetas

    }

    fun cargarDesdeFirebase() {
        val database = FirebaseDatabase.getInstance()
        val myRecetaRef = database.getReference("recetas")
        listRecetas.clear()
        // tipoDeReceta = "ensaladas"

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.child(tipoDeReceta).children) {
                    val recetaServer = dato.getValue(Receta::class.java)
                    recetaServer?.let { listRecetas.add(it) }
                }
                recetasRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }

        myRecetaRef.addValueEventListener(postListener)
    }

    override fun onItemClick(receta: Receta) {
        val action = ListaRecetasFragmentDirections.actionListaRecetasFragmentToIngredientesYPasosFragment(receta)
        findNavController().navigate(action)
    }






}

