package com.alejoestrada.misdeudores.mapas.activityAux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejoestrada.misdeudores.Drawer.DrawerActivity
import com.alejoestrada.misdeudores.R
import kotlinx.android.synthetic.main.activity_variado_activity.*

class variado_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_variado_activity)

        regresar_variado_button3.setOnClickListener {
            val intent = Intent(this, DrawerActivity ::class.java)
            startActivity(intent)
            finish()
        }
    }
}