package com.alejoestrada.misdeudores.Recetas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentNavRecetasBinding
import kotlinx.android.synthetic.main.fragment_nav_recetas.*

//,RecetasRVAdapter.OnItemClickListener
class nav_recetas : Fragment() {
    private lateinit var binding: FragmentNavRecetasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_recetas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNavRecetasBinding.bind(view)

        desayuno_imageView.setOnClickListener {
            val action = nav_recetasDirections.actionNavRecetasToListaRecetasFragment("desayunos")
            findNavController().navigate(action)
        }
        almuerzo_imageView.setOnClickListener {
            val action = nav_recetasDirections.actionNavRecetasToListaRecetasFragment("comidas")
            findNavController().navigate(action)

        }

        cenas_imageView.setOnClickListener {
            val action = nav_recetasDirections.actionNavRecetasToListaRecetasFragment("cenas")
            findNavController().navigate(action)
        }

        ensaladas_imageView.setOnClickListener {
            val action = nav_recetasDirections.actionNavRecetasToListaRecetasFragment("ensaladas")
            findNavController().navigate(action)
        }
    }
}



