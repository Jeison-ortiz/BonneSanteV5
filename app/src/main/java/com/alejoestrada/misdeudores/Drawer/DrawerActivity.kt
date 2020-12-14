package com.alejoestrada.misdeudores.Drawer

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.AlimentosTab.nav_alimentos_tab
import com.alejoestrada.misdeudores.data.server.Usuario
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var doubleBackToExitPressedOnce = false
    private lateinit var item: MenuItem
    //private lateinit var auth: FirebaseAuth
    private var content: FrameLayout? = null


    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
        when (item.itemId) {
            R.id.nav_alimentos_tab -> {
                val fragment = nav_alimentos_tab()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }


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


        val user = FirebaseAuth.getInstance().currentUser

        val database = FirebaseDatabase.getInstance()
        val usuarioRef = database.getReference("usuarios")

        usuarioRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data: DataSnapshot in snapshot.children) {
                    val usuarioActual = data.getValue(Usuario::class.java)
                    if (usuarioActual?.id.equals(user?.uid)) {
                        name.text = usuarioActual?.name
                        email.text = usuarioActual?.correo
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })







        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_alimentos_tab,
                R.id.nav_dieta,
                R.id.nav_imc,
                R.id.restaurantes_google_Fragment,
                R.id.nav_recetas,
                R.id.nav_perfil

            ), drawerLayout
        )




        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    private fun profileData() {

    }


/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cerrar_sesion -> {
                val auth = FirebaseAuth.getInstance().signOut()
                goToLoginActivity()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    fun goToLoginActivity(){
        val intent= Intent(this, LogInActivity2::class.java)
        startActivity(intent)
        finish()
    }*/
    /*
    val user = FirebaseAuth.getInstance().currentUser

        val database= FirebaseDatabase.getInstance()
        val usuarioRef= database.getReference("")
        usuarioRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for ( data : DataSnapshot in snapshot.children) {
                    val usuarioActual = data.getValue(Usuario::class.java)
                    if (usuarioActual?.id == user?.uid) {

                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    */


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


