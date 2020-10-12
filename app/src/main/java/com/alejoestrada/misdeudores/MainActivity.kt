package com.alejoestrada.misdeudores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_sesionOut ->{
                val intent = Intent(this, LogInActivity2::class.java)
                intent.putExtra("correo",ex)
                intent.putExtra("contrasena",cx)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}