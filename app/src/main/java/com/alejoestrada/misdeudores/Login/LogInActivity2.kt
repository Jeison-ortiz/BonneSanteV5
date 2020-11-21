package com.alejoestrada.misdeudores.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.Drawer.DrawerActivity
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.Registro.RegistroActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in2.*


class LogInActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = LogInActivity2::class.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in2)
        auth = FirebaseAuth.getInstance()

        sesion_button.setOnClickListener {
            val correo = valorcorreo_login.text.toString()
            val contrasena = editTextTextPersonName2.text.toString()

            loginWithFirebase(correo, contrasena)
        }

        button_registrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginWithFirebase(correo: String, contrasena: String) {
        auth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    goToDrawer()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()


                }


            }

    }

    private fun goToDrawer() {
        val intent = Intent(this, DrawerActivity::class.java)
        startActivity(intent)
        finish()
    }


}

