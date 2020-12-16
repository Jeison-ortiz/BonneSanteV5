package com.alejoestrada.misdeudores.Dieta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alejoestrada.misdeudores.AlimentosTab.AlimentosRVAdapter
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.Alimento
import com.alejoestrada.misdeudores.data.server.Usuario
import com.alejoestrada.misdeudores.databinding.FragmentDietaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_dieta.*
import kotlin.random.Random



class nav_dieta : Fragment() {
    private lateinit var binding : FragmentDietaBinding
    private lateinit var diaSeleccionado : String
    var listAlimentos: MutableList<Alimento> = mutableListOf()
    var listAlimentos2: MutableList<Alimento> = mutableListOf()
    val lisMeals = listOf("almuerzo","cena","desayuno","merienda")
    val listMealAlmuerzo = listOf("carbohidratos","carnes","frutas","lacteos","variado","vegetales")
    val listMealCena = listOf("carbohidratos","carnes","lacteos","variado","vegetales")
    val listMealDesayuno = listOf("carbohidratos","frutas","lacteos","variado")
    val listMealMerienda = listOf("carbohidratos","frutas","lacteos","variado")
    val listOfListMeal = listOf(listMealAlmuerzo,listMealCena,listMealDesayuno,listMealMerienda)
    var lista : MutableList<String> = mutableListOf()
    var listaAux : MutableList<String> = mutableListOf()
    var listaDesayunoAlea: MutableList<String> = mutableListOf()
    var listaAlmuerzoAlea: MutableList<String> = mutableListOf()
    var listaCenaAlea: MutableList<String> = mutableListOf()
    var listaMediaMananaAlea: MutableList<String> = mutableListOf()
    var listaMediaTardeAlea: MutableList<String> = mutableListOf()
    val randomValuesDesayuno = List(3) {
        Random.nextInt(0, 4)}
    val randomValuesAlmuerzo = List(3) { Random.nextInt(0, 6) }
    val randomValuesCena = List(3) { Random.nextInt(0, 5) }
    val randomValuesMerienda = List(3) { Random.nextInt(0, 4) }
    val listaPersonal = listOf(listaDesayunoAlea,listaAlmuerzoAlea,listaCenaAlea,listaMediaMananaAlea,listaMediaTardeAlea)

    private lateinit var alimentosRVAdapter: AlimentosRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dieta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDietaBinding.bind(view)

        val args: nav_dietaArgs by navArgs()
        diaSeleccionado = args.diaSeleccion

        binding.diaTextView.text = diaSeleccionado.capitalize()
        for (i in 0 until 3) {
                var numDe = randomValuesDesayuno[i]
                var numAl = randomValuesAlmuerzo[i]
                var numMM = randomValuesMerienda[i]
                var numMT = randomValuesMerienda[i]
                var numCe = randomValuesCena[i]
                listaDesayunoAlea.add(listMealDesayuno[numDe])
                listaAlmuerzoAlea.add(listMealAlmuerzo[numAl])
                listaMediaMananaAlea.add(listMealMerienda[numMM])
                listaMediaTardeAlea.add(listMealMerienda[numMT])
                listaCenaAlea.add(listMealCena[numCe])

            //Toast.makeText(context, randomValuesDesayuno[0].toString(), Toast.LENGTH_SHORT).show()

            }

       // cargarDesdeFarebase()

        }


    private fun cargarDesdeFarebase() {
        val database = FirebaseDatabase.getInstance()
        val myalimentosRef = database.getReference("alimentos")
        var i = 0
        val user = FirebaseAuth.getInstance().currentUser
        val idDieta = user?.uid.toString()


        listAlimentos.clear()

        //  for (mealDay in lisMeals) {
        for (foodMeal in listaPersonal) {
            var mealDay = lisMeals.get(i)
            for (piramide in foodMeal) {
                val postListener_comida = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (comida: DataSnapshot in snapshot.child(mealDay).child(piramide).children) {

                            val alimentosServer: Alimento? = comida.getValue(Alimento::class.java)

                            if (mealDay == "almuerzo"){
                                input_almuerzo_textView.text = alimentosServer?.id + " " + alimentosServer?.porcion+ " " + alimentosServer?.porcion
                            }else if (mealDay == "desayuno"){
                                input_desayuno_TextView.text = alimentosServer?.id + " " + alimentosServer?.porcion+ " " + alimentosServer?.porcion
                            }else if (mealDay == "cena"){
                                input_cena_textView.text = alimentosServer?.id + " " + alimentosServer?.porcion+ " " + alimentosServer?.porcion
                            }else if (mealDay == "merienda"){
                                input_mediamanana_TextView.text =alimentosServer?.id + " " + alimentosServer?.porcion+ " " + alimentosServer?.porcion
                                input_media_tarde_textView.text = alimentosServer?.id + " " + alimentosServer?.porcion+ " " + alimentosServer?.porcion
                            }



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


}