package com.alejoestrada.misdeudores.Perfil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.Recetas.ListaRecetasFragmentArgs
import com.alejoestrada.misdeudores.Registro.RegistroActivity.Companion.TAG
import com.alejoestrada.misdeudores.data.server.Usuario
import com.alejoestrada.misdeudores.databinding.FragmentDialogEditBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_dialog_edit.*
import java.util.HashMap


class DialogEditFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogEditBinding
    private lateinit var idUsuario: String
    private lateinit var editValor: String
    private lateinit var tipoPerfil: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_dialog_edit, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDialogEditBinding.bind(view)

        val args: DialogEditFragmentArgs by navArgs()
        tipoPerfil = args.TipoPerfil

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val usuarioRef = database.getReference("usuarios")

         binding.editNombreTexInputEditText.hint = tipoPerfil

        binding.guardarButton.setOnClickListener {
            editValor = binding.editNombreTexInputEditText.text.toString()
            idUsuario = user?.uid.toString()
            val postlistener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data: DataSnapshot in snapshot.children) {
                        val usuarioActual: Usuario? = data.getValue(Usuario::class.java)
                        if (usuarioActual?.id == user?.uid) {

                            if(tipoPerfil == "name"){
                                val chilUpdates = HashMap<String, Any>()
                                chilUpdates["name"] = editValor
                                idUsuario.let { usuarioRef.child(it).updateChildren(chilUpdates)
                                }
                            }
                            if (tipoPerfil == "correo"){

                                val chilUpdates = HashMap<String, Any>()
                                chilUpdates["correo"] = editValor
                                idUsuario.let { usuarioRef.child(it).updateChildren(chilUpdates)
                                }

                                user!!.updateEmail(editValor)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Log.d(TAG, "User email address updated.")
                                        }else{
                                            Log.d(TAG, "error.")
                                        }
                                    }
                            }

                            if (tipoPerfil == "telefono"){
                                val chilUpdates = HashMap<String, Any>()
                                chilUpdates["telefono"] = editValor
                                idUsuario.let { usuarioRef.child(it).updateChildren(chilUpdates)
                                }
                            }

                            if(tipoPerfil == "contrasena"){
                                val chilUpdates = HashMap<String, Any>()
                                chilUpdates["telefono"] = editValor
                                idUsuario.let { usuarioRef.child(it).updateChildren(chilUpdates)}

                                user!!.updatePassword(editValor)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Log.d(TAG, "User password updated.")
                                            }else{
                                                Log.d(TAG, "error.")
                                            }
                                        }

                            }


                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            usuarioRef.addListenerForSingleValueEvent(postlistener)
        }








    }


}