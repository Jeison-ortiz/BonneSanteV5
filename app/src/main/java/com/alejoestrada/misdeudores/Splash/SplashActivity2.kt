package com.alejoestrada.misdeudores.Splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.Drawer.DrawerActivity
import com.alejoestrada.misdeudores.Login.LogInActivity2
import com.alejoestrada.misdeudores.R
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)


        val timer = Timer()

        //hay que colocar tiempo en el splash
        timer.schedule(
            timerTask {
                val auth = FirebaseAuth.getInstance().currentUser
                if (auth == null) {
                    goToLoginActivity()
                } else {
                    goToDrawerActivity()
                }

            }, 2000

        )

    }

    fun goToLoginActivity() {
        //intent disparo de actvidades
        val intent = Intent(this, LogInActivity2::class.java)
        //start activity
        startActivity(intent)
        finish() // destruyo parte interior
    }

    fun goToDrawerActivity() {
        val intent = Intent(this, DrawerActivity::class.java)
        startActivity(intent)
        finish()
    }


}