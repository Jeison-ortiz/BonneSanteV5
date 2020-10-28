package com.alejoestrada.misdeudores.Drawer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alejoestrada.misdeudores.R
import com.google.android.material.navigation.NavigationView


class DrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // mainActivity()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val correo_recibido3 = intent.extras
        val mail3 = correo_recibido3?.getString("correo_usuario")
        val name_recibido = intent.extras
        val nombreR = name_recibido?.getString("name")

        val navigationView =
            findViewById<View>(R.id.nav_view) as NavigationView
        val header = navigationView.getHeaderView(0)
        val name: TextView = header.findViewById(R.id.name_textview)
        val email: TextView = header.findViewById(R.id.correo_drawer)
        email.text = mail3
        name.text = nombreR
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_alimentos,
                R.id.nav_dieta,
                R.id.nav_imc,
                R.id.nav_restaurante,
                R.id.nav_recetas,
                R.id.nav_perfil
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val exit = Intent(Intent.ACTION_MAIN)
        exit.addCategory(Intent.CATEGORY_HOME)
        startActivity(exit)
        finish()
    }


}