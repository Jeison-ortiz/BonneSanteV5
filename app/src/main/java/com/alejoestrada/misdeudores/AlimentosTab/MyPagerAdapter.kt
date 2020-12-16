package com.alejoestrada.misdeudores.AlimentosTab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alejoestrada.misdeudores.data.Alimento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var listAlimentos: MutableList<Alimento> = mutableListOf()


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                alimentosTotales()
            }
            else -> {
                return alimentosFavs()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Alimentos"
            else -> {
                return "Alimentos Favoritos"
            }
        }
    }
    /*private fun cargarDesdeFirebase() {
        val user = FirebaseAuth.getInstance().currentUser
        val idDieta = user?.uid.toString()
        val database = FirebaseDatabase.getInstance()
        val dietaRef = database.getReference("dieta")
        val alimentosRVAdapter = AlimentosRVAdapter(listAlimentos as ArrayList<Alimento>)
        var i = 0
        listAlimentos.clear()

        //  for (mealDay in lisMeals) {
        val postListener_comida = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (comida: DataSnapshot in snapshot.child(idDieta).child("Favoritos").children) {
                    val alimentosServer: Alimento? = comida.getValue(Alimento::class.java)
                    alimentosServer?.let { listAlimentos.add(it) }
                }
                alimentosRVAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }
            //alimentosFavsAdapter.notifyDataSetChanged()
        }
        dietaRef.addListenerForSingleValueEvent(postListener_comida)



        // }
    }*/
}