package com.alejoestrada.misdeudores.Registro

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.Login.LogInActivity2
import com.alejoestrada.misdeudores.R
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class RegistroActivity : AppCompatActivity() {


    companion object {
        private const val EMPTY= ""
        private const val SPACE= " "

    }
    var datedenaissance=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val datosrecibidos= intent.extras
        val numeroEnviado= datosrecibidos?.getInt("numero")
        //Toast.makeText(this, "El numero enviado es $numeroEnviado", Toast.LENGTH_SHORT).show()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        lafecha_PICKER.setOnClickListener {
            val dpd= DatePickerDialog(this, DatePickerDialog.OnDateSetListener {view: DatePicker, mYear: Int, mMonth: Int, mDay : Int ->
                val monthn= mMonth+1
                datedenaissance =  "" + mDay + "/" + monthn + "/" + mYear

            }, year, month, day)
            dpd.show()


        }

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
                val intent = Intent(this, LogInActivity2::class.java)
                intent.putExtra("correo", correo)
                intent.putExtra("contrasena", contrasena)
                intent.putExtra("nombre", nombre)
                startActivity(intent)
                finish()
            }




        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LogInActivity2::class.java )
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Metodo","onResume")
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