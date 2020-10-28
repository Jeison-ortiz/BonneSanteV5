package com.alejoestrada.misdeudores.Main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var cx:String?=""
    var ex:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val correo_recibido2= intent.extras
        val mail2= correo_recibido2?.getString("correo_usuario")
        ex=mail2
        val contrasena_recibida2 = intent.extras
        val contrasena3 = contrasena_recibida2?.getString("passUser")
        cx=contrasena3
        MostrarEmail_ediText.setText(mail2)
        MostarContra_editText.setText(contrasena3)

        //Toast.makeText(this, "la $contrasena3 y la $mail2", Toast.LENGTH_LONG).show()


    }


}