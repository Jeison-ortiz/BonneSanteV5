package com.alejoestrada.misdeudores.resetPassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import android.util.Log
import android.widget.Toast
import com.alejoestrada.misdeudores.Login.LogInActivity2
import com.alejoestrada.misdeudores.R
import kotlinx.android.synthetic.main.activity_reset_passsword.*

class reset_passsword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_passsword)

        auth = FirebaseAuth.getInstance()

        forgot_password_button.setOnClickListener {

            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (editTforgot_extTextEmailAddress.text.toString().isNullOrEmpty()) {
                val toast1 =
                    Toast.makeText(this, "No has ingresado el Email", Toast.LENGTH_SHORT).show()
            } else {
                auth.sendPasswordResetEmail(
                    editTforgot_extTextEmailAddress.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser

                            val toast2 = Toast.makeText(
                                this,
                                "Link de reseteo fue enviado",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {

                            val toast3 = Toast.makeText(
                                this,
                                "El correo de reseteo no pudo ser enviado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            //goToLoginActivity()
        }

        back_imageView2.setOnClickListener {
            goToLoginActivity()
        }


    }
    private fun goToLoginActivity (){
        val intent = Intent(this, LogInActivity2 ::class.java)
        startActivity(intent)
        finish()
    }

}

