package com.alejoestrada.misdeudores.mapas.activityAux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejoestrada.misdeudores.Drawer.DrawerActivity
import com.alejoestrada.misdeudores.R
import kotlinx.android.synthetic.main.activity_vegano_activity.*

class vegano_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vegano_activity)

        vegano_regresar_button2.setOnClickListener {
            val intent = Intent(this, DrawerActivity ::class.java)
            startActivity(intent)
            finish()
        }
    }


}