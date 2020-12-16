package com.alejoestrada.misdeudores.Perfil

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alejoestrada.misdeudores.Login.LogInActivity2
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Usuario
import com.alejoestrada.misdeudores.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_perfil.*

class nav_perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }
 // hola
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPerfilBinding.bind(view)

        binding.cerrarPerfilButton.setOnClickListener {
            val intent = Intent(context, LogInActivity2::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        binding.editNombreImageButton.setOnClickListener{
            val action = nav_perfilDirections.actionNavPerfilToDialogEditFragment("name")
            findNavController().navigate(action)
        }

        binding.editCorreoImageButton.setOnClickListener{
            val action = nav_perfilDirections.actionNavPerfilToDialogEditFragment("correo")
            findNavController().navigate(action)
        }

        binding.editTelefonoImageButton.setOnClickListener{
            val action = nav_perfilDirections.actionNavPerfilToDialogEditFragment("telefono")
            findNavController().navigate(action)
        }

        binding.editeditContrasenaImageButton.setOnClickListener{
            val action = nav_perfilDirections.actionNavPerfilToDialogEditFragment("contrasena")
            findNavController().navigate(action)
        }

        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val usuarioRef = database.getReference("usuarios")

        usuarioRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data: DataSnapshot in snapshot.children) {
                    val usuarioActual = data.getValue(Usuario::class.java)
                    if (usuarioActual?.id.equals(user?.uid)) {
                        nombrePerfil_textView?.text = usuarioActual?.name
                        correoPerfil_textView?.text = usuarioActual?.correo
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

/*    editarFoto_imageView.setOnClickListener{
         dispatchTakePictureIntent()
     }*/


    }

    /*    private fun dispatchTakePictureIntent() {
          Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { TakePictureIntent ->
              TakePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                  startActivityForResult(TakePictureIntent, REQUEST_IMAGE_CAPTURE)
              }
          }
      }*/


    companion object
}

//alejandro estrada