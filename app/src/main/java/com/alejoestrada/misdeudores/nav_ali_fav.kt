package com.alejoestrada.misdeudores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.alejoestrada.misdeudores.AlimentosTab.AlimentosFavoritosaAdapter
import com.alejoestrada.misdeudores.data.Alimento
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import com.google.firebase.database.ValueEventListener


class nav_ali_fav : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var vista_: View
    lateinit var myContext: FragmentActivity
    var listAlimentos: MutableList<Alimento> = mutableListOf()
    private lateinit var alimentosFavsAdapter : AlimentosFavoritosaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        tabLayout = inflater.inflate(R.layout.fragment_nav_ali_fav, container, false).findViewById(R.id.tabLayout)
        viewPager = inflater.inflate(R.layout.fragment_nav_ali_fav, container, false).findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        //alimentosFavsAdapter = AlimentosFavoritosaAdapter(listAlimentos as ArrayList<Alimento>,this@nav_ali_fav)

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
       // val adapter = MyAdapter(context,FragmentManager)
       // viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                //cargarDesdeFirebase()
                Toast.makeText(context, "paso de tab", Toast.LENGTH_SHORT).show()


            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        return inflater.inflate(R.layout.fragment_nav_ali_fav, container, false)

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


}