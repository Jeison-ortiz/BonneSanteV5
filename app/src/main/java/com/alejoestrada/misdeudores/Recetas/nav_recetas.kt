package com.alejoestrada.misdeudores.Recetas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentNavRecetasBinding


class nav_recetas : Fragment() {
    private lateinit var binding:FragmentNavRecetasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_recetas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentNavRecetasBinding.bind(view)
    }


}