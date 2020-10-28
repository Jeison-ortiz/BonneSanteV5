package com.alejoestrada.misdeudores.Login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.Drawer.DrawerActivity
import com.alejoestrada.misdeudores.Main.MainActivity
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.Registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_log_in2.*


class LogInActivity2 : AppCompatActivity() {
    var cx=""
    var ex= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in2)

        val correo_recibido = intent.extras
        val mail = correo_recibido?.getString("correo")

        val contrasena_recibida = intent.extras
        val contrasena = contrasena_recibida?.getString("contrasena")

        val nombre_recibido = intent.extras
        val nombre = nombre_recibido?.getString("nombre")


        // valores que se ingresan en el login


        sesion_button.setOnClickListener {
            val correo_usuario = valorcorreo_login.text.toString()
            cx = correo_usuario
            val contrasena_Usuario = editTextTextPersonName2.text.toString()
            ex = contrasena_Usuario
            if (mail == correo_usuario && contrasena == contrasena_Usuario) {
                val intent = Intent(this, DrawerActivity::class.java)
                intent.putExtra("correo_usuario", correo_usuario)
                intent.putExtra("passUser", contrasena_Usuario)
                intent.putExtra("name", nombre)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)

                //goToMainActivity()

            } else {
                Toast.makeText(this,"Correo ó Contaseña Invalida // Email or Password Incorrect",Toast.LENGTH_LONG).show()
            }

        }

        button_registrar.setOnClickListener {
            val intent =Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}

