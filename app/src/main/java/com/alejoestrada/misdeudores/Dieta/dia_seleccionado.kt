package com.alejoestrada.misdeudores.Dieta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentDiaSeleccionadoBinding
import kotlinx.android.synthetic.main.fragment_dia_seleccionado.*

class dia_seleccionado : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentDiaSeleccionadoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dia_seleccionado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDiaSeleccionadoBinding.bind(view)

        lunes_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("lunes")
            findNavController().navigate(action)
        }
        martes_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("martes")
            findNavController().navigate(action)
        }
        miercoles_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("miercoles")
            findNavController().navigate(action)
        }
        jueves_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("jueves")
            findNavController().navigate(action)
        }
        viernes_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("viernes")
            findNavController().navigate(action)
        }
        sabado_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("sabado")
            findNavController().navigate(action)
        }
        domingo_button.setOnClickListener {
            val action =  dia_seleccionadoDirections.actionDiaSeleccionadoToNavDieta2("domingo")
            findNavController().navigate(action)
        }




    }

}