package com.alejoestrada.misdeudores.Recetas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentIngredientesYPasosBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.combine

class IngredientesYPasosFragment : Fragment() {

    private lateinit var binding: FragmentIngredientesYPasosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredientes_y_pasos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentIngredientesYPasosBinding.bind(view)

        val args: IngredientesYPasosFragmentArgs by navArgs()
        val recetaDetalle = args.IngredientesYpasos

        Picasso.get().load(recetaDetalle.foto).into(binding.fotoRecetaDetalleImageView)
        binding.ingredientestextView.text = recetaDetalle.ingredientes
        binding.pasosTextView.text = recetaDetalle.preparacion
       // val args: IngredientesYPasosFragment by navArgs()
        //val recetaDetalle = args
    }

  /*  override fun onViewCeated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetalleBinding.bind(view)

        val args: DetalleFragmentArgs by navArgs()
        val deudorDetalle = args.deudorSeleccionado
        binding.nombreTextView.text = deudorDetalle.nombre

    }*/










}
