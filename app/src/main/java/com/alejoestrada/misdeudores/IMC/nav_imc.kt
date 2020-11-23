package com.alejoestrada.misdeudores.IMC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Usuario
import com.alejoestrada.misdeudores.databinding.FragmentIMCBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_i_m_c.*
import java.util.*


class nav_imc : Fragment() {
    private lateinit var binding: FragmentIMCBinding
    private lateinit var peso: String
    private lateinit var altura: String
    private lateinit var idDieta: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_m_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentIMCBinding.bind(view)

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val dietaRef = database.getReference("dieta")

        binding.imcImageButton.setOnClickListener {
            imc_imageButton.isEnabled = false
            peso = binding.valorPesoIMCTextView.text.toString()
            altura = binding.valorAlturaTextView.text.toString()
            idDieta = user?.uid.toString()
            val postlistener = object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data: DataSnapshot in snapshot.children) {

                        val usuarioActual: Usuario? = data.getValue(Usuario::class.java)
                        if (usuarioActual?.id == user?.uid) {
                            val chilUpdates = HashMap<String, Any>()
                            chilUpdates["altura"] = altura
                            chilUpdates["peso"] = peso
                            idDieta.let { dietaRef.child(it).updateChildren(chilUpdates) }
                        }
                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
            dietaRef.addValueEventListener(postlistener)
            imc_imageButton.isEnabled = true


        }


        /* binding.imcImageButton.setOnClickListener{
             //val laDieta= Dieta(idDieta,altura,peso,"")
             val chilUpdates = HashMap<String, Any>()
             chilUpdates["altura"]=altura
             chilUpdates["peso"]= peso
             idDieta?.let { dietaRef.child(it).updateChildren(chilUpdates) }
             val toast= Toast.makeText(context,"entre al bton",Toast.LENGTH_SHORT).show()
         }*/

    }


}