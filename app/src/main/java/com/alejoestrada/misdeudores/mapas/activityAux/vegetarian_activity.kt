package com.alejoestrada.misdeudores.mapas.activityAux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejoestrada.misdeudores.Drawer.DrawerActivity
import com.alejoestrada.misdeudores.R
import kotlinx.android.synthetic.main.activity_vegetarian_activity.*

class vegetarian_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vegetarian_activity)

        regresar_vege_button2.setOnClickListener {
            val intent = Intent(this, DrawerActivity ::class.java)
            startActivity(intent)
            finish()
        }
    }
}