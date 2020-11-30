package com.alejoestrada.misdeudores.mapas.fragmentsAux

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentVariadoFRBinding
import com.alejoestrada.misdeudores.mapas.restaurantes_google_Fragment


class variado_FR : Fragment() {

    private  lateinit var  binding: FragmentVariadoFRBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_variado__f_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentVariadoFRBinding.bind(view)

        binding.regresarVariadoButton.setOnClickListener {
            val intent = Intent(context, restaurantes_google_Fragment ::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }


    }

}