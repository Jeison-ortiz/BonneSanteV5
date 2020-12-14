package com.alejoestrada.misdeudores.AlimentosTab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejoestrada.misdeudores.R
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.data.Alimento
import com.alejoestrada.misdeudores.data.server.Usuario
import com.alejoestrada.misdeudores.databinding.FragmentAlimentosFavsBinding
import com.alejoestrada.misdeudores.databinding.FragmentAlimentosFavsListaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class alimentosFavs : Fragment(), AlimentosFavoritosaAdapter.OnItemClickListener{

    private lateinit var binding:FragmentAlimentosFavsListaBinding
    private lateinit var alimentosFavsAdapter :AlimentosFavoritosaAdapter
    var listAlimentos: MutableList<Alimento> = mutableListOf()
    var flag : Boolean = false
    val lisMeals = listOf("almuerzo","cena","desayuno","merienda")
    val listMealAlmuerzo = listOf("carbohidratos","carnes","frutas","lacteos","variado","vegetales")
    val listMealCena = listOf("carbohidratos","carnes","lacteos","variado","vegetales")
    val listMealDesayuno = listOf("carbohidratos","frutas","lacteos","variado")
    val listMealMerienda = listOf("carbohidratos","frutas","lacteos","variado")
    val listOfListMeal = listOf(listMealAlmuerzo,listMealCena,listMealDesayuno,listMealMerienda)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alimentos_favs_lista, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAlimentosFavsListaBinding.bind(view)
        binding.alimentosFavsRecyclerView.invalidate()
        binding.alimentosFavsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        binding.alimentosFavsRecyclerView.setHasFixedSize(true)
        alimentosFavsAdapter = AlimentosFavoritosaAdapter(listAlimentos as ArrayList<Alimento>,this@alimentosFavs)
        binding.alimentosFavsRecyclerView.adapter = alimentosFavsAdapter
        cargarDesdeFirebase()
        alimentosFavsAdapter.notifyDataSetChanged()


    }

    private fun cargarDesdeFirebase() {
        val user = FirebaseAuth.getInstance().currentUser
        val idDieta = user?.uid.toString()
        val database = FirebaseDatabase.getInstance()
        val dietaRef = database.getReference("dieta")
        var i = 0
        listAlimentos.clear()

        //  for (mealDay in lisMeals) {
                val postListener_comida = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (comida: DataSnapshot in snapshot.child(idDieta).child("Favoritos").children) {
                            val alimentosServer: Alimento? = comida.getValue(Alimento::class.java)
                            alimentosServer?.let { listAlimentos.add(it) }
                        }
                       alimentosFavsAdapter.notifyDataSetChanged()

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                    //alimentosFavsAdapter.notifyDataSetChanged()
                }
                dietaRef.addListenerForSingleValueEvent(postListener_comida)



        // }
    }

    override fun onItemClick(alimento: Alimento, pos : Int) {
        val user = FirebaseAuth.getInstance().currentUser

        val idDieta = user?.uid.toString()
        val database2 = FirebaseDatabase.getInstance()
        val dietaRef = database2.getReference("dieta")

        val postlistener = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (data: DataSnapshot in snapshot.children) {

                    val usuarioActual: Usuario? = data.getValue(Usuario::class.java)
                    if (usuarioActual?.id == user?.uid) {
                        idDieta?.let { dietaRef.child(idDieta).child("Favoritos").child(alimento?.id.toString()).removeValue() }
                    }
                }
                alimentosFavsAdapter.notifyDataSetChanged()
                alimentosFavsAdapter.notifyItemRemoved(pos)

                cargarDesdeFirebase()

            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        dietaRef.addListenerForSingleValueEvent(postlistener)
    }


}






