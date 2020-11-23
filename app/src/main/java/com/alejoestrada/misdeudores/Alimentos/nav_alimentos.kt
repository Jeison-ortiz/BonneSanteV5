package com.alejoestrada.misdeudores.Alimentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.Alimento
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.alejoestrada.misdeudores.databinding.FragmentAlimentosBinding


class nav_alimentos: Fragment() {

    private lateinit var binding: FragmentAlimentosBinding

    var listAlimentos: MutableList<Alimento> = mutableListOf()
    val lisMeals = listOf("almuerzo","cena","desayuno","merienda")
    val listMealAlmuerzo = listOf("carbohidratos","carnes","frutas","lacteos","variado","vegetales")
    val listMealCena = listOf("carbohidratos","carnes","lacteos","variado","vegetales")
    val listMealDesayuno = listOf("carbohidratos","frutas","lacteos","variado")
    val listMealMerienda = listOf("carbohidratos","frutas","lacteos","variado")
    val listOfListMeal = listOf(listMealAlmuerzo,listMealCena,listMealDesayuno,listMealMerienda)
    private lateinit var alimentosRVAdapter: AlimentosRVAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alimentos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAlimentosBinding.bind(view)

        binding.alimentosRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.alimentosRecyclerView.setHasFixedSize(true) // para que el tamaño sea constante
        alimentosRVAdapter = AlimentosRVAdapter(listAlimentos as ArrayList<Alimento>)
        binding.alimentosRecyclerView.adapter = alimentosRVAdapter
        cargarDesdeFarebase()
        alimentosRVAdapter.notifyDataSetChanged() // se hace para notificar que cambiaron los datos porque se envio una lista vacia
    }

    private fun cargarDesdeFarebase() {
        val database = FirebaseDatabase.getInstance()
        val myalimentosRef = database.getReference("alimentos")
        var i = 0
        listAlimentos.clear()

      //  for (mealDay in lisMeals) {

            for (foodMeal in listOfListMeal) {
                var mealDay = lisMeals.get(i)
                for (piramide in foodMeal) {

                    val postListener_comida = object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (comida: DataSnapshot in snapshot.child(mealDay).child(piramide).children) {
                                val alimentosServer: Alimento? = comida.getValue(Alimento::class.java)
                                alimentosServer?.let { listAlimentos.add(it) }
                            }
                            alimentosRVAdapter.notifyDataSetChanged()
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }
                        //  alimentosRVAdapter.notifyDataSetChanged()
                    }
                    myalimentosRef.addValueEventListener(postListener_comida)
                }
                i += 1
            }
       // }
    }





    private fun gotoMeals() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.children) {
                    val deudorServer = dato.getValue(Alimento::class.java)
                    deudorServer?.let { listAlimentos.add(it) }
                }
                alimentosRVAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
    }

    }

    /*private fun cargarDesdeDatabase() {
        val deudorDAO = MisDeudores.database.DeudorDAO()
        listDeudores = deudorDAO.getDeudores()
    }*/


