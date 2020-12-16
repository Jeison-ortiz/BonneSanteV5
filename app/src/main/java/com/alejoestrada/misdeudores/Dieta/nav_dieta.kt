package com.alejoestrada.misdeudores.Dieta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentDietaBinding


class nav_dieta : Fragment() {
    private lateinit var binding : FragmentDietaBinding
    private lateinit var diaSeleccionado : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dieta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDietaBinding.bind(view)

      val args: nav_dietaArgs by navArgs()
        diaSeleccionado = args.diaSeleccion

       binding.diaTextView.text = diaSeleccionado.capitalize()


    }
}