package com.alejoestrada.misdeudores

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejoestrada.misdeudores.Login.LogInActivity2


class cerrar_Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cerrar_, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val intent = Intent(context, LogInActivity2::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


    }


}
/*
fun goToLoginActivity() {
    val intent = Intent(context, LogInActivity2::class.java)
    startActivity(intent)
    finish()
}
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.menu_overflow,menu)
    return true
}


override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        R.id.menu_logout -> {
            val auth = FirebaseAuth.getInstance().signOut()
            goToLoginActivity()
        }
        else -> super.onOptionsItemSelected(item)
    }
    return true
}


}*/