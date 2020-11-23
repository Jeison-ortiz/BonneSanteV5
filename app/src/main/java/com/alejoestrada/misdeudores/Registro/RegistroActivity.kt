package com.alejoestrada.misdeudores.Registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.Login.LogInActivity2
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Dieta
import com.alejoestrada.misdeudores.data.server.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth


    companion object {
        private const val EMPTY = ""
        private const val SPACE = " "
        val TAG = RegistroActivity::class.simpleName

    }

    var datedenaissance = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        auth = FirebaseAuth.getInstance()

        val datosrecibidos = intent.extras
        val numeroEnviado = datosrecibidos?.getInt("numero")
        //Toast.makeText(this, "El numero enviado es $numeroEnviado", Toast.LENGTH_SHORT).show()





        registrar_button.setOnClickListener {

            val nombre = nombre_edit_view.text.toString()
            val correo = correo_edit_text.text.toString()
            val telefono = telefono_edit_text.text.toString()
            val contrasena = contrasena_edit_text.text.toString()
            val repcontrasena = repetircontra_edit_text.text.toString()
            var genero =
                if (masculino_radiobutton.isChecked) getString(R.string.masculino) else getString(
                    R.string.femenino
                )
            val peso_usuario = valor_peso_edit_text.toString()
            val altura_usuario = valor_altura_edit_text.toString()


            val dieta = tipos_dieta.selectedItem

            if (contrasena == "" && repcontrasena == "" || contrasena != repcontrasena || contrasena.length < 6) {
                textView3.text =
                    "Contraseñas no coinciden, vuelva a intentar (deben tener al menos 6 digitos)// Passwords don't match, please check (the password needs at least 6 characters) "
            } else if (contrasena == repcontrasena && contrasena.length >= 6) {
                registroEnFirebase(nombre, correo, contrasena, telefono)
            }


        }
    }

    private fun registroEnFirebase(
        nombre: String,
        correo: String,
        contrasena: String,
        telefono: String
    ) {

        auth.createUserWithEmailAndPassword(correo, contrasena)  //crea el usuario en firebase
            .addOnCompleteListener(this) { task ->                  //escuchador una vez se ejecute la tarea
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val uid: String? = auth.currentUser?.uid    //aqui se cree el id automatico

                    crearUsuarioEnBaseDatos(uid, correo, nombre, telefono)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                // ...
            }

    }

    private fun crearUsuarioEnBaseDatos(
        uid: String?,
        correo: String,
        nombre: String,
        telefono: String
    ) {
        val database = FirebaseDatabase.getInstance()
        val myUsersReference = database.getReference("usuarios")

        val usuario = Usuario(uid, nombre, correo, telefono)

        uid?.let { myUsersReference.child(uid).setValue(usuario) }

        val database2 = FirebaseDatabase.getInstance()

        val myRefDieta = database2.getReference("dieta")

        val dieta = Dieta(uid, "", "", "")

        uid?.let { myRefDieta.child(uid).setValue(dieta) }



        goToLoginActivity()

    }


    private fun goToLoginActivity() {
        val intent = Intent(this, LogInActivity2::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Metodo", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Metodo","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Metodo","onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Metodo","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Metodo","onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Metodo","onRestart")
    }


}