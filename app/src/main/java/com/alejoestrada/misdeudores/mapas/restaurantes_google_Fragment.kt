package com.alejoestrada.misdeudores.mapas

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentRestaurantesGoogleBinding
import com.alejoestrada.misdeudores.mapas.activityAux.variado_activity
import com.alejoestrada.misdeudores.mapas.activityAux.vegano_activity
import com.alejoestrada.misdeudores.mapas.activityAux.vegetarian_activity
import com.alejoestrada.misdeudores.mapas.fragmentsAux.vegetariano_FR
import kotlinx.android.synthetic.main.fragment_restaurantes_google_.*


class restaurantes_google_Fragment : Fragment() {
        private lateinit var binding : FragmentRestaurantesGoogleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurantes_google_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Restaurantes"

        binding= FragmentRestaurantesGoogleBinding.bind(view)

        vegan_imageView2.setOnClickListener {
            val intent = Intent(context, vegano_activity ::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

        }
        vegetariano_imageView.setOnClickListener {
            val intent = Intent(context, vegetarian_activity ::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        variado_imageView.setOnClickListener {
            val intent = Intent(context, variado_activity ::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }


}