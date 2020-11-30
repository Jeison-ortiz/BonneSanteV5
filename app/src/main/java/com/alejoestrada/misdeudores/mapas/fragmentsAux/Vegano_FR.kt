package com.alejoestrada.misdeudores.mapas.fragmentsAux

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentVeganoFRBinding
import com.alejoestrada.misdeudores.mapas.restaurantes_google_Fragment

class vegano_FR : Fragment() {

    private lateinit var binding : FragmentVeganoFRBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vegano__f_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentVeganoFRBinding.bind(view)

        binding.veganoRegresarButton.setOnClickListener {
            val intent = Intent(context, restaurantes_google_Fragment ::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }


}